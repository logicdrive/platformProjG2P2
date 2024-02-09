import fs from 'fs'
import replace from 'replace-in-file'


function main() {
    const settings = readJsonFile('./input/settings.json')


    copyAllRecursive('./template/base', './output/base')
    fs.renameSync('./output/base/src/main/java/SERVICE_INFO_PACKAGE_NAME',  
              `./output/base/src/main/java/${settings.SERVICE_INFO.PACKAGE_NAME}`)

    
    const options = {
        files: ['./output/base/src/main/**/*.*', './output/base/pom.xml', 
                './output/base/command/docker/value/**/*.*', './output/base/command/kubernetes/value/**/*.*'],
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

main()