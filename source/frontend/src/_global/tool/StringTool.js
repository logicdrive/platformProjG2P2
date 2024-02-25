class StringTool {
    static toTitle(text) {
        return text[0].toUpperCase() + text.slice(1)
    }

    static limitText(text, limitLength) {
        if(text.length > limitLength) {
            return text.slice(0, limitLength) + "..."
        } else {
            return text
        }
    }
}

export default StringTool;