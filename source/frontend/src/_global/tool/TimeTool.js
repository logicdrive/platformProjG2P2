class TimeTool {
    static prettyDateString(dateString) {
        return new Date(dateString).toISOString().replace('T', ' ').slice(0, -5)
    }

    static prettyOnlyDateString(dateString) {
        return TimeTool.prettyDateString(dateString).split(" ")[0]
    }
}

export default TimeTool;