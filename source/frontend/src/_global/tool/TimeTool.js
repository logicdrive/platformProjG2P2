class TimeTool {
    static SecondToAudioTime(sec) {
        return String(Math.floor(sec/60)).padStart(2, '0') + ":" + String(sec%60).padStart(2, '0')
    }

    static prettyDateString(dateString) {
        return new Date(dateString).toISOString().replace('T', ' ').slice(0, -5)
    }

    static prettyOnlyDateString(dateString) {
        return TimeTool.prettyDateString(dateString).split(" ")[0]
    }
}

export default TimeTool;