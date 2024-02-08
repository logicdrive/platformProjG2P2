function main() {

    // Read data from BookGeneratorService.json
    let eventStormingData = require('./BookGeneratorService.json')
    let elementDic = eventStormingData.elements
    let relationDic = eventStormingData.relations

    let toRelationData = getToRelationData(getPrettyRelationData(relationDic))
    let prettyElementDic = getprettyElementDic(elementDic, toRelationData)


    let prettyEventStormingData = {}
    IteratorUtil.iterateValueFromDic(prettyElementDic, (element) => {
        if(element.type != "BoundedContext") return
        
        prettyEventStormingData[element.name] = {
            id: element.id,
            name: element.name,
            elements: {
                "Aggregate": {},
                "Event": {},
                "Policy": {},
                "Command": {},
                "View": {}
            }
        }
    })

    IteratorUtil.iterateValueFromDic(prettyEventStormingData, (boundedContext) => {
        IteratorUtil.iterateValueFromDic(prettyElementDic, (element) => {
            if(element.boundedContextId != boundedContext.id) return

            if(element.type == "Policy") {
                prettyEventStormingData[boundedContext.name].elements[element.type][element.id] = {
                    ...element,
                    from: element.from.map((from) => {
                        return prettyElementDic[from.id]
                    })
                }
            }
            else
                prettyEventStormingData[boundedContext.name].elements[element.type][element.id] = element
        })
    })


    const fs = require('fs')
    fs.writeFileSync('prettyEventStormingData.json', JSON.stringify(prettyEventStormingData, null, 2))
}


function getToRelationData(prettyRelationData) {
    let toRelationData = {}
    IteratorUtil.iterateValueFromDic(prettyRelationData, (relation) => {
        let toId = relation.to.id
        if(toRelationData[toId] == null) {
            toRelationData[toId] = []
        }
        
        toRelationData[toId].push(relation.from)
    })

    return toRelationData
}

function getPrettyRelationData(relationDic) {
    let prettyRelationData = {}
    IteratorUtil.iterateValueFromDic(relationDic, (element) => {
        if(element == null || element._type == null) return
        if(element._type != "org.uengine.modeling.model.Relation") return

        prettyRelationData[element.id] = {
            id: element.id,

            from: {
                id: element.sourceElement.id,
                type: element.sourceElement._type,
                name: element.sourceElement.name
            },

            to: {
                id: element.targetElement.id,
                type: element.targetElement._type,
                name: element.targetElement.name
            }
        }
    })

    return prettyRelationData
}

function getprettyElementDic(elementDic, toRelationData) {
    let prettyElementDic = {}

    IteratorUtil.iterateValueFromDic(elementDic, (element) => {
        if (element == null || element._type == null) return
        let type = element._type.split('.').pop()

        switch(type) {
            case "BoundedContext":
                prettyElementDic[element.id] = {
                    id: element.id,
                    name: element.name,
                    type: type
                }
                break

            case "Aggregate":
                prettyElementDic[element.id] = {
                    boundedContextId: element.boundedContext.id,
                    id: element.id,
                    name: element.name,
                    type: type,
                    attributes: element.aggregateRoot.fieldDescriptors.map((fieldDescriptor) => {
                        return {
                            name: fieldDescriptor.name,
                            className: fieldDescriptor.className
                        }
                    })
                }
                break
            
            case "Event":
                prettyElementDic[element.id] = {
                    boundedContextId: element.boundedContext.id,
                    id: element.id,
                    name: element.name,
                    type: type,
                    attributes: element.fieldDescriptors.map((fieldDescriptor) => {
                        return {
                            name: fieldDescriptor.name,
                            className: fieldDescriptor.className
                        }
                    })
                }
                break
            
            case "Policy":
                prettyElementDic[element.id] = {
                    boundedContextId: element.boundedContext.id,
                    id: element.id,
                    name: element.name,
                    type: type,
                    from: toRelationData[element.id]
                }
                break

            case "Command":
                prettyElementDic[element.id] = {
                    boundedContextId: element.boundedContext.id,
                    id: element.id,
                    name: element.name,
                    type: type,
                    apiPath: element.controllerInfo.apiPath,
                    method: element.controllerInfo.method
                }
                break
            
            case "View":
                prettyElementDic[element.id] = {
                    boundedContextId: element.boundedContext.id,
                    id: element.id,
                    name: element.name,
                    type: type,
                    attributes: element.fieldDescriptors.map((fieldDescriptor) => {
                        return {
                            name: fieldDescriptor.name,
                            className: fieldDescriptor.className
                        }
                    })
                }
                break
        }
    })

    return prettyElementDic
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