/********************
Last names: De los Reyes, Gayamo & Quinones
Language: Katlin
Paradigm(s): Object Oriented Programming & Functional Constructs
********************/

package display

val nnsIntime = "0900"
val nsInTime = "1800"
var display = Displays()

fun main() {
    while(true){
        start()         //will always run
    }
}
fun start (){
    when(display.displayOpening()){
        1 -> employeeDetails()
        2 -> {
            println("\n\nSystem Terminated...")
            System.exit(0)
        }
    }
}
fun employeeDetails() {
    var filledDays = 0

    while (filledDays < 14) {
        val whatWeekday = display.selectDay()               //what Day (ie., Monday, tues etc.)
        when (whatWeekday) {
            8 -> start()
            9 -> {
                display.summaryCollected()
                System.exit(0)
            }
            else -> {
                setDayInformation(whatWeekday)
                filledDays++
            }
        }
    }
}
fun setDayInformation (dayOfWeek: Int) {
    var inTime = ""         //set IN Time
    var workAttendance = 0  // Present or Absent
    var shift = 0           //Night Shift or Not
    var outTime = 0         //OUT Time of employee
    var countOT = 0
    var extraOUT = 0        //extraOut
    var dayType = 0         //What type of Day is the work

    //set the weekdays must be filled else cannot exit
    workAttendance = display.workStatus()           //absent or not

    //if Present
    if (workAttendance == 1) {
        shift = display.shiftOption()                   //Night Shift or Not

        println("\n======== EMPLOYEE DETAILS =========")
        when (shift) {
            1 -> {
                inTime = nnsIntime                                  //Non Night Shift
                print("[a] IN Time : $inTime\n")                    // in time of the employee depends on shift status (NNS or NS)
                print("[b] OUT Time \n")
                outTime = display.optOutTime()                      //options of Out time, count and see if how many overtime
                if (outTime > 4) {
                    countOT = 4
                    extraOUT = outTime - 4
                } else {
                    countOT = outTime
                }
            }
            2 -> {
                inTime = nsInTime          // Night Shift, 2200 start
                print("[a] IN Time : $inTime\n")                    // in time of the employee depends on shift status (NNS or NS)
                print("[b] OUT Time \n")
                outTime = display.optOutTime()                      //options of Out time, count and see if how many overtime
                if(outTime > 4){
                    countOT = outTime - 4       //if there is overtime
                }
            }
        }
    } else {     //Absent
        inTime = nnsIntime
        print("[a] IN Time : $inTime\n")
        var out = nnsIntime
        outTime = out.toInt()                 //absent : the outTime is same with inTime indicating absent
        print("[b] OUT Time : 0$outTime\n")
    }
    do {
        println("[c] Day Type")
        display.typeOfDay()                             //What type of days/Holiday are list
        dayType = readLine()?.toIntOrNull() ?: -1
    } while (dayType > 5 || dayType <= 0)

    var daySalary = 500.00          //daily rate
    var payRate = 0.00


    if(outTime == nnsIntime.toInt()){       //absent in Normal day, no salary for that day
        daySalary = 0.00
    }
    else if (outTime == 0){                                      //sakto yung uwi pero nagwork sa special days
        if(dayType != 1){                                   //employee worked on not normal day
            payRate = display.specialDays(dayType)
            daySalary = display.rateAndDT(daySalary, payRate)
        }
    }
    else if (inTime == nnsIntime && outTime !=0){            //start 900 and has overtime, not 1800 dismissal, NNS with overtime
        var initialHour = 0.00
        var extra = 0.00
        var anotherExtra = 0.00

        payRate = display.nonNSRate(dayType)
        initialHour = display.salaryCalculator(daySalary,countOT, payRate)
        daySalary += initialHour

        if(extraOUT != 0){                                  //if there is extra hour for NNS, means exceeded then it should add the NS rate
            payRate = display.nightRate(dayType)
            extra = display.salaryCalculator(500.00,extraOUT, payRate)
            daySalary += extra
        }
        if(dayType !=1){            //if worked on special days
            daySalary = 500.00
            payRate = display.specialDays(dayType)
            anotherExtra = display.rateAndDT (500.00, payRate)
            daySalary += anotherExtra
        }
        if (initialHour != 0.00 && extra !=0.00 && anotherExtra != 0.00){
            daySalary = initialHour + extra + anotherExtra
        }
    }
    else if (inTime == nsInTime && outTime !=0){            //Night Shift start at 2200 and has overtime, NS with overtime
        if(dayType == 1){
            daySalary += display.normalNightSalary(daySalary,countOT)                    //Night Shift start at 2200 IN NORMAL DAY
        }
        else {
            payRate = display.nightRate(dayType)
            daySalary += display.salaryCalculator(daySalary,countOT, payRate)
        }
    }

    display.setDay(dayOfWeek, workAttendance, shift, inTime, outTime,countOT,extraOUT, dayType, daySalary)
//    display.getDetails(dayOfWeek).printCheck()       //for printing checking values of each days
}