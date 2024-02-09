import fs from 'fs'
import replace from 'replace-in-file'


function applyCallback(settings, prettyEventStormingData) {
    createDomainEntities(settings, prettyEventStormingData)

    IteratorUtil.iterateValueFromDic(prettyEventStormingData[settings.TARGET_BOUNDARY_CONTEXT].elements.Event, (event) => {
    
    })
}

function main() {
    createServiceByUsingTemplate('./input/settings.json', './input/prettyEventStormingData.json', applyCallback)
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
            to: [aggregate.name, aggregate.name.toLowerCase() + "s"]
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
                './output/base/command/copilot/collectAllJavaCodes.sh'],
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


class IteratorUtil {
    static iterateValueFromDic(dic, callback) {
        Object.entries(dic).forEach((item, index) => {
            let element = item[1]
            callback(element)
        })
    }
}

main()