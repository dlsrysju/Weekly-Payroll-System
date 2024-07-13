package display.display


class Slots {
    private val mainEmpDayOfWeek: Details
    constructor(dayOfWeek: Int, attendance: Int, shiftSched: Int, inTime: String, outTime: Int, countOT : Int, extraOutTime : Int, dayType: Int, daySalary : Double) {
        mainEmpDayOfWeek = Details(dayOfWeek, attendance, shiftSched, inTime, outTime, countOT, extraOutTime, dayType, daySalary)
    }

    fun getEmpDetails(): Details {
        return mainEmpDayOfWeek
    }
}