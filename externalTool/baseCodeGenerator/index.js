import fs from 'fs'
import replace from 'replace-in-file'


function applyCallback(settings, prettyEventStormingData) {
    createDomainEntities(settings, prettyEventStormingData)
    createEvents(settings, prettyEventStormingData)
    createCommands(settings, prettyEventStormingData)
    createPolicy(settings, prettyEventStormingData)
    createViews(settings, prettyEventStormingData)
}

function main() {
    createServiceByUsingTemplate('./input/settings.json', './input/prettyEventStormingData.json', applyCallback)
}


function createPolicy(settings, prettyEventStormingData) {
    IteratorUtil.iterateValueFromDic(prettyEventStormingData[settings.TARGET_BOUNDARY_CONTEXT].elements.Policy, (policy) => {        
        policy.from.forEach((fromEvent) => {
        
        const FROM_EVENT_NAME = fromEvent.name
        const FROM_EVENT_NAME_FUNCTION = lowerFrontChar(fromEvent.name)
        const FROM_EVENT_ATTRIBUTE = (fromEvent.attributes.map((attribute) => {
            return `\tprivate ${attribute.className} ${attribute.name};`
        })).join('\n')
        const POLICY_NAME = policy.name
        
        
        const fromEventPath = `./output/base/src/main/java/${settings.SERVICE_INFO.PACKAGE_NAME}/_global/event`
        const fromEventOutputPath = `${fromEventPath}/${FROM_EVENT_NAME}.java`

        fs.cpSync('./template/files/RawEventTemplate.java', fromEventOutputPath, {overwrite: true})
        
        const fromEventOptions = {
            files: [fromEventOutputPath],
            from: [/\[\[TEMPLATE\.FROM_EVENT_NAME\]\]/g, /\[\[TEMPLATE\.FROM_EVENT_ATTRIBUTE\]\]/g],
            to: [FROM_EVENT_NAME, FROM_EVENT_ATTRIBUTE]
        }
        replace.sync(fromEventOptions)


        const policyPath = `./output/base/src/main/java/${settings.SERVICE_INFO.PACKAGE_NAME}/policy`
        const policyOutputPath = `${policyPath}/${FROM_EVENT_NAME}_${POLICY_NAME}_Policy.java`

        fs.cpSync('./template/files/PolicyTemplate.java', policyOutputPath, {overwrite: true})
        
        const policyOptions = {
            files: [policyOutputPath],
            from: [/\[\[TEMPLATE\.FROM_EVENT_NAME\]\]/g, /\[\[TEMPLATE\.FROM_EVENT_NAME_FUNCTION\]\]/g, /\[\[TEMPLATE\.POLICY_NAME\]\]/g],
            to: [FROM_EVENT_NAME, FROM_EVENT_NAME_FUNCTION, POLICY_NAME]
        }
        replace.sync(policyOptions)

        })
    })
}

function createCommands(settings, prettyEventStormingData) {
    IteratorUtil.iterateValueFromDic(prettyEventStormingData[settings.TARGET_BOUNDARY_CONTEXT].elements.Command, (command) => {
        const ENTITY_NAME = settings.TARGET_BOUNDARY_CONTEXT
        const ENDPOINT_TITLE = upperFrontChar(command.name)
        const ENDPOINT_FUNCTION = command.name
        
        const commandPath = `./output/base/src/main/java/${settings.SERVICE_INFO.PACKAGE_NAME}/endPoint`
        const commandOutputPath = `${commandPath}/${ENDPOINT_TITLE}EndPoints.java`


        fs.cpSync('./template/files/EndPointsTemplate.java', commandOutputPath, {overwrite: true})
        
        const commandOptions = {
            files: [commandOutputPath],
            from: [/\[\[TEMPLATE\.ENTITY_NAME\]\]/g, /\[\[TEMPLATE\.ENDPOINT_TITLE\]\]/g, /\[\[TEMPLATE\.ENDPOINT_FUNCTION\]\]/g],
            to: [ENTITY_NAME, ENDPOINT_TITLE, ENDPOINT_FUNCTION]
        }
        replace.sync(commandOptions)
    })
}

function createEvents(settings, prettyEventStormingData) {
    IteratorUtil.iterateValueFromDic(prettyEventStormingData[settings.TARGET_BOUNDARY_CONTEXT].elements.Event, (event) => {
        const eventPath = `./output/base/src/main/java/${settings.SERVICE_INFO.PACKAGE_NAME}/_global/event`
        const eventOutputPath = `${eventPath}/${event.name}.java`


        fs.cpSync('./template/files/EventTemplate.java', eventOutputPath, {overwrite: true})
        
        const eventOptions = {
            files: [eventOutputPath],
            from: [/\[\[TEMPLATE\.NAME\]\]/g, /\[\[TEMPLATE\.EVENT_NAME\]\]/g],
            to: [upperFrontChar(settings.TARGET_BOUNDARY_CONTEXT), event.name]
        }
        replace.sync(eventOptions)
    })
}

function createViews(settings, prettyEventStormingData) {
    IteratorUtil.iterateValueFromDic(prettyEventStormingData[settings.TARGET_BOUNDARY_CONTEXT].elements.View, (view) => {
        const functionViewName = lowerFrontChar(view.name)
        const viewRootPath = `./output/base/src/main/java/${settings.SERVICE_INFO.PACKAGE_NAME}/${functionViewName}`
        fs.mkdirSync(viewRootPath, { recursive: true })

        const domainPath = `${viewRootPath}/domain`
        const aggregateOutputPath = `${domainPath}/${view.name}.java`
        const repositoryOutputPath = `${domainPath}/${view.name}Repository.java` 
        const manageServiceOutputPath = `${domainPath}/${view.name}ManageService.java`


        fs.cpSync('./template/files/EntityTemplate.java', aggregateOutputPath, {overwrite: true})
        
        let entityAttributeStrs = []
        view.attributes.forEach((attribute) => {
            if(["id", "createdDate", "updatedDate"].includes(attribute.name)) return

            entityAttributeStrs.push(`\tprivate ${attribute.className} ${attribute.name};\n`)
        })

        const entityOptions = {
            files: [aggregateOutputPath],
            from: [/\[\[TEMPLATE\.NAME\]\]/g, /\[\[TEMPLATE\.ATTRIBUTES\]\]/g, "package [[SERVICE_INFO.PACKAGE_NAME]].domain;"],
            to: [view.name, entityAttributeStrs.join('\n'), `package ${settings.SERVICE_INFO.PACKAGE_NAME}.${functionViewName}.domain;`]
        }
        replace.sync(entityOptions)


        fs.cpSync('./template/files/RepositoryTemplate.java', repositoryOutputPath, {overwrite: true})

        const repositoryOptions = {
            files: [repositoryOutputPath],
            from: [/\[\[TEMPLATE\.NAME\]\]/g, /\[\[TEMPLATE\.RES_PATH\]\]/g, "package [[SERVICE_INFO.PACKAGE_NAME]].domain;"],
            to: [view.name, lowerFrontChar(view.name) + "s", `package ${settings.SERVICE_INFO.PACKAGE_NAME}.${functionViewName}.domain;`]
        }
        replace.sync(repositoryOptions)


        fs.cpSync('./template/files/ManageServiceTemplate.java', manageServiceOutputPath, {overwrite: true})

        const manageServiceOptions = {
            files: [manageServiceOutputPath],
            from: [/\[\[TEMPLATE\.NAME\]\]/g, "package [[SERVICE_INFO.PACKAGE_NAME]].domain;"],
            to: [view.name, `package ${settings.SERVICE_INFO.PACKAGE_NAME}.${functionViewName}.domain;`]
        }
        replace.sync(manageServiceOptions)
    })
}

function createDomainEntities(settings, prettyEventStormingData) {
    IteratorUtil.iterateValueFromDic(prettyEventStormingData[settings.TARGET_BOUNDARY_CONTEXT].elements.Aggregate, (aggregate) => {
        const domainPath = `./output/base/src/main/java/${settings.SERVICE_INFO.PACKAGE_NAME}/domain`
        const eventBasePath = `./output/base/src/main/java/${settings.SERVICE_INFO.PACKAGE_NAME}/_global/eventBase`
        const aggregateOutputPath = `${domainPath}/${aggregate.name}.java`
        const repositoryOutputPath = `${domainPath}/${aggregate.name}Repository.java` 
        const manageServiceOutputPath = `${domainPath}/${aggregate.name}ManageService.java`
        const eventBaseOutputPath = `${eventBasePath}/${aggregate.name}Event.java`
        

        fs.cpSync('./template/files/EntityTemplate.java', aggregateOutputPath, {overwrite: true})
        
        let entityAttributeStrs = []
        aggregate.attributes.forEach((attribute) => {
            if(["id", "createdDate", "updatedDate"].includes(attribute.name)) return

            entityAttributeStrs.push(`\tprivate ${attribute.className} ${attribute.name};\n`)
        })

        const entityOptions = {
            files: [aggregateOutputPath],
            from: [/\[\[TEMPLATE\.NAME\]\]/g, /\[\[TEMPLATE\.ATTRIBUTES\]\]/g],
            to: [aggregate.name, entityAttributeStrs.join('\n')]
        }
        replace.sync(entityOptions)


        fs.cpSync('./template/files/RepositoryTemplate.java', repositoryOutputPath, {overwrite: true})

        const repositoryOptions = {
            files: [repositoryOutputPath],
            from: [/\[\[TEMPLATE\.NAME\]\]/g, /\[\[TEMPLATE\.RES_PATH\]\]/g],
            to: [aggregate.name, lowerFrontChar(aggregate.name) + "s"]
        }
        replace.sync(repositoryOptions)


        fs.cpSync('./template/files/ManageServiceTemplate.java', manageServiceOutputPath, {overwrite: true})

        const manageServiceOptions = {
            files: [manageServiceOutputPath],
            from: [/\[\[TEMPLATE\.NAME\]\]/g],
            to: [aggregate.name]
        }
        replace.sync(manageServiceOptions)


        fs.cpSync('./template/files/BaseEventTemplate.java', eventBaseOutputPath, {overwrite: true})
        
        let eventBaseAttributeStrs = []
        aggregate.attributes.forEach((attribute) => {
            eventBaseAttributeStrs.push(`\tprotected ${attribute.className} ${attribute.name};`)
        })

        const eventBaseOptions = {
            files: [eventBaseOutputPath],
            from: [/\[\[TEMPLATE\.NAME\]\]/g, /\[\[TEMPLATE\.ATTRIBUTES\]\]/g],
            to: [aggregate.name, eventBaseAttributeStrs.join('\n')]
        }
        replace.sync(eventBaseOptions)
    })
}

function createServiceByUsingTemplate(settingPath, prettyEventStormingDataPath, applyCallback) {
    const settings = readJsonFile(settingPath)
    const prettyEventStormingData = readJsonFile(prettyEventStormingDataPath)

    copyAllRecursive('./template/base', './output/base')
    fs.renameSync('./output/base/src/main/java/SERVICE_INFO_PACKAGE_NAME',  
              `./output/base/src/main/java/${settings.SERVICE_INFO.PACKAGE_NAME}`)


    applyCallback(settings, prettyEventStormingData)
    

    const options = {
        files: ['./output/base/src/main/**/*.*', './output/base/pom.xml', 
                './output/base/command/docker/value/**/*.*', './output/base/command/kubernetes/value/**/*.*',
                './output/base/command/copilot/*.sh'],
        from: [/\[\[SERVICE_INFO\.PACKAGE_NAME\]\]/g, /\[\[SERVICE_INFO\.SERVICE_NAME\]\]/g, 
            /\[\[SERVICE_INFO\.OPEN_PORT\]\]/g, /\[\[SERVICE_INFO\.HOST_PORT\]\]/g,
            /\[\[SERVICE_INFO\.DOCKER_IMAGE_NAME\]\]/g, /\[\[SERVICE_INFO\.DOCKER_SERVICE_NAME\]\]/g,
            /\[\[SERVICE_INFO\.DOKCER_IMAGE_UESR_NAME\]\]/g],
        to: [settings.SERVICE_INFO.PACKAGE_NAME, settings.SERVICE_INFO.SERVICE_NAME, 
            settings.SERVICE_INFO.OPEN_PORT, settings.SERVICE_INFO.HOST_PORT,
            settings.SERVICE_INFO.DOCKER_IMAGE_NAME, settings.SERVICE_INFO.DOCKER_SERVICE_NAME,
            settings.SERVICE_INFO.DOKCER_IMAGE_UESR_NAME]
    }
    replace.sync(options)
}


function readJsonFile(path) {
    return JSON.parse(fs.readFileSync(path, 'utf8'))
}

function copyAllRecursive(srcPath, destPath) {    
    if (fs.existsSync(destPath)) {
        fs.rmSync(destPath, { recursive: true })
    }
    
    fs.cpSync(srcPath, destPath, { recursive: true })
}

function upperFrontChar(str) {
    return str[0].toUpperCase() + str.slice(1)
}

function lowerFrontChar(str) {
    return str[0].toLowerCase() + str.slice(1)
}


class IteratorUtil {
    static iterateValueFromDic(dic, callback) {
        Object.entries(dic).forEach((item, index) => {
            let element = item[1]
            callback(element)
        })
    }
}

main()