package display

import display.display.Details
import display.display.Slots

class Displays {
    //default values
    private val daySlots = Array(7) {
        Slots(it, 0, 0, "0900", 0, 0,0, 1, 0.00)
    }
    fun displayOpening() : Int? {
        var choice = 0
        do {
            println("\n=========================")
            println("| WEEKLY PAYROLL SYSTEM |")
            println("=========================")
            println("1) Continue")
            println("2) Exit")
            print("Enter choice [1-2]: ")
            choice = readln().toInt()
        }while(choice > 2 || choice < 1)
        return choice
    }

    //Select and Present the 7 days of a Week
    fun selectDay () : Int{
        var choice = 0
        var totalSalary = 0.00
        do {
            println("\n==========================")
            println("|    DAYS OF THE WEEK    |")
            println("==========================")
            for (i in 1..7) {
                println("$i) ${getDetails(i).weekDay(i)}        |   ${getDetails(i).getTodaySalary()}")
                totalSalary += getDetails(i).getTodaySalary()
            }
            println("Total :           $totalSalary")
            println("--------------------------")
            println("8) Back")
            println("9) Collect ")
            print("Enter choice [1-9]:  ")
            try {
                choice = readLine()!!.toInt()
            } catch (e: NumberFormatException) {
                println("Invalid input. Please enter a number.")
                continue
            }
        } while (choice > 9 || choice < 1)
        return choice
    }
    fun workStatus () : Int {
        var choice = 0
        do {
            println("\n=========================")
            println("|      WORK STATUS      |")
            println("=========================")
            println("1) Present ")
            println("2) Absent")
            println("3) Back")
            print("Enter choice [1-3]: ")
            choice = readln().toInt()
        }while(choice > 3 || choice < 1)
        return choice
    }
    fun shiftOption () : Int {
        var choice = 0
        do {
            println("\n=========================")
            println("|     SHIFT SCHEDULE    |")
            println("=========================")
            println("1) NON-Night Shift")               //0900
            println("2) Night Shift")                   //2200
            println("3) Back")
            print("Enter choice [1-3]: ")
            choice = readln().toInt()
        }while(choice > 2 || choice < 1)
        return choice
    }
    fun optOutTime () : Int {
        var choice = 0
        do {
            println("\t0) 1800")
            println("\t1) 1900      |       7) 0100")
            println("\t2) 2000      |       8) 0200")
            println("\t3) 2100      |       9) 0300")
            println("\t4) 2200      |      10) 0400")
            println("\t5) 2300      |      10) 0500")
            println("\t6) 0000      |      12) 0600")
            print("Select Type [0-12]: ")
            choice = readln().toInt()
        }while(choice > 12  || choice < -1)
        return choice
    }
    fun typeOfDay () {
        for (i in 1..6) {
            println("\t$i) ${getDetails(i).typeOfDay(i)}")
        }
        print("Select Type: ")
    }
    fun setDay(dayOfWeek: Int, attendance: Int, shiftSched: Int, inTime: String, outTime: Int, countOT : Int, extraOut : Int, dayType: Int, daySalary : Double) {
        if (dayOfWeek >= 1 && dayOfWeek <= 7) {
            daySlots[dayOfWeek - 1] = Slots(dayOfWeek, attendance, shiftSched, inTime, outTime, countOT, extraOut, dayType, daySalary)
        }
    }
    fun getDetails(slotNum: Int): Details {
        return daySlots[slotNum-1].getEmpDetails()
    }
    fun nonNSRate(dayType : Int) : Double{
        var rate = 0.00
        when(dayType){
            1 -> rate = 1.25        //Normal Day
            2 -> rate = 1.69        //Rest Day
            3 -> rate = 1.69        //Special Non-Working Day
            4 -> rate = 1.95        //Special NWD and RD
            5 -> rate = 2.60        //Regular Holiday
            6 -> rate = 3.38        // RH and RD
        }
        return rate
    }
    fun nightRate(dayType : Int) : Double{
        var rate = 0.00
        when(dayType){
            1 -> rate = 1.375        //Normal Day
            2 -> rate = 1.859       //Rest Day
            3 -> rate = 1.859        //Special Non-Working Day
            4 -> rate = 2.145        //Special NWD and RD
            5 -> rate = 2.860        //Regular Holiday
            6 -> rate = 3.718        // RH and RD
        }
        return rate
    }
    fun specialDays (dayType: Int) : Double{
        var rate = 0.00
        when(dayType){
            2 -> rate = 1.30        //Rest Day
            3 -> rate = 1.30        //Special Non-Working Day
            4 -> rate = 1.50        //Special NWD and RD
            5 -> rate = 2.00        //Regular Holiday
            6 -> rate = 2.60        // RH and RD
        }
        return rate
    }
    fun salaryCalculator(dailyRate : Double, countOT: Int, payRate : Double) : Double{
        var maxHrs = 8
        var addTotal = countOT*dailyRate/maxHrs*payRate
        return addTotal
    }
    fun normalNightSalary(dailyRate : Double, countOT: Int) : Double{
        var maxHrs = 8
        var nightSD = 1.10
        var addTotal = countOT*dailyRate/maxHrs*nightSD
        return addTotal
    }
    fun rateAndDT(dailyRate : Double, payRate: Double) : Double{
        var addTotal = dailyRate*payRate
        return addTotal
    }
    fun summaryCollected(){
        var totalSalary = 0.00
        for (i in 1..7) {
            totalSalary += getDetails(i).getTodaySalary()
        }
        println("\n--------------------------------")
        println(" SALARY COLLECTED FOR THIS WEEK")
        println("--------------------------------")
        println("Monday         |        ${getDetails(1).getTodaySalary()}")
        println("Tuesday        |        ${getDetails(2).getTodaySalary()}")
        println("Wednesday      |        ${getDetails(3).getTodaySalary()}")
        println("Thursday       |        ${getDetails(4).getTodaySalary()}")
        println("Friday         |        ${getDetails(5).getTodaySalary()}")
        println("Saturday       |        ${getDetails(6).getTodaySalary()}")
        println("Sunday         |        ${getDetails(7).getTodaySalary()}")
        println("=================================")
        println("Total  :                ${totalSalary}")
    }
}