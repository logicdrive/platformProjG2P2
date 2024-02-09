function sampleCode() {
    // Copy all directory and files in ./template/base to ./output/base
    const fs = require('fs')
    fs.cpSync('./template/base', './output/base', { recursive: true })
}