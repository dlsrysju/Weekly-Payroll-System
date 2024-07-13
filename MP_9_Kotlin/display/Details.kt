package display.display

class Details(var dayToday: Int, var isPresent: Int, var isShiftSched: Int, var empIN: String, var empOUT: Int, var countOT : Int, var extraOutTime : Int, var typeWorkDay: Int, var daySalary : Double) {
    fun weekDay (weekday: Int) : String{
        var day = ""
        when(weekday){
            1 -> day += "MON"
            2 -> day += "TUE"
            3 -> day += "WED"
            4 -> day += "THU"
            5 -> day += "FRI"
            6 -> day += "SAT"
            7 -> day += "SUN"
        }
        return day
    }
    fun isPresent (attendance : Int) : String{
        var att = ""
        when(attendance){
            1 -> att += "Present"
            2 -> att += "Absent"
        }
        return att
    }
    fun shiftSched (shiftTime : Int) : String{
        var shift = ""
        when(shiftTime){
            1 -> shift += "NON-Night Shift"
            2 -> shift += "Night Shift"
        }
        return shift
    }
    fun timeCon (outTime: Int) : String{
        var time = ""
        when(outTime){
            0 -> time += "1800"
            1 -> time += "1900"
            2 -> time += "2000"
            3 -> time += "2100"
            4 -> time += "2200"
            5 -> time += "2300"
            6 -> time += "0000"
            7 -> time += "0100"
            8 -> time += "0200"
            9 -> time += "0300"
            10 -> time += "0400"
            11 -> time += "0500"
            12 -> time += "0600"
            else -> time += "0900"
        }
        return time
    }
    fun typeOfDay (typeDay: Int) : String{
        var day = ""
        when(typeDay){
            1 -> day += "Normal Day"
            2 -> day += "Rest Day"
            3 -> day += "Special Non-Working Day"
            4 -> day += "Special Non-Working Day and Rest Day"
            5 -> day += "Regular Holiday"
            6 -> day += "Regular Holiday and Rest Day"
        }
        return day
    }
    fun printCheck(){
        println("\nWhat day : ${weekDay(getDay())}")
        println("Attendance : ${isPresent(getAttendance())}")
        println("Shift : ${shiftSched(geShiftSched())}")
        println("IN Time : ${getInTime()}")
        println("OUT Time : ${timeCon(getOutTime())}")
        println("Type of Day : ${typeOfDay(getTypeDay())}")
        println("Hrs Overtime : ${getCountOvertime()} (${getExtra()})")
        println("Today's Salary : ${getTodaySalary()}")
    }
    fun getDay(): Int {
        return dayToday
    }
    fun getAttendance(): Int {
        return isPresent
    }

    fun geShiftSched(): Int {
        return isShiftSched
    }

    fun getInTime(): String {
        return empIN
    }
    fun getOutTime(): Int {
        return empOUT
    }
    fun getCountOvertime () : Int {
        return countOT
    }
    fun getExtra() : Int{
        return extraOutTime
    }
    fun getTypeDay(): Int {
        return typeWorkDay
    }
    fun getTodaySalary() : Double{
        return daySalary
    }
}