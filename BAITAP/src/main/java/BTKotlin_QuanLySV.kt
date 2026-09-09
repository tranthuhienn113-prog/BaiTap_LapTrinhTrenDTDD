import java.util.Scanner
data class Student(
    val studentID: String,
    val fullName: String,
    val age: Int,
    val major: String,
    val gpa: Double
)
fun main() {
    val scanner = Scanner(System.`in`)
    val studentList = mutableListOf(
        Student("2415053122216", "Tran Thi Thu Hien", 20, "Cong nghe thong tin", 3.7),
        Student("30210264123", "Le Nguyen Viet Nhat", 19, "Khoa hoc may tinh", 3.8),
        Student("20208158091", "Pham Thi Yen Ny", 19, "Tai chinh ngan hang", 4.0),
        Student("30207164268", "Nguyen Huynh Yen Nha", 19, "Dieu duong", 3.6),
        Student("2411120022", "Thi My Nhan", 19, "Dieu duong", 3.5)
    )
    var choice: Int
    do {
        println("\n========== STUDENT MANAGEMENT ==========")
        println("1. Add student")
        println("2. Display all students")
        println("3. Search student")
        println("4. Calculate average GPA")
        println("5. Find student with highest GPA")
        println("6. Remove student")
        println("=== CAC YEU CAU CHUC NANG BO SUNG ===")
        println("7. Dem so sinh vien co GPA >= 8.0 (Hoac >= 3.5 tuy he quy đoi)")
        println("8. Dem so sinh vien co GPA < 5.0 (Hoac < 2.0)")
        println("9. Tinh GPA trung binh theo nganh")
        println("10. Tim sinh vien co GPA cao nhat")
        println("11. Tim sinh vien lon tuoi nhat")
        println("12. Tim sinh vien co GPA nam trong khoang 7.0 -> 8.5 (Hoac 3.0 -> 3.8)")
        println("13. Tim tat ca sinh vien thuoc mot nganh")
        println("14. Tim sinh vien theo mot phan ten")
        println("15. Sap xep sinh vien theo GPA giam dan")
        println("16. Hien thi 3 sinh vien co GPA cao nhat")
        println("17. Sap xep sinh vien theo tuoi")
        println("18. Sap xep sinh vien theo ten")
        println("0. Exit")
        println("========================================")
        print("Choose: ")
        choice = scanner.nextInt()
        scanner.nextLine()
        when (choice) {
            1 -> {
                print("Nhap Student ID: ")
                val id = scanner.nextLine()
                print("Nhap Full Name: ")
                val name = scanner.nextLine()
                print("Nhap Age: ")
                val age = scanner.nextInt()
                scanner.nextLine()
                print("Nhap Major: ")
                val major = scanner.nextLine()
                print("Nhap GPA: ")
                val gpa = scanner.nextDouble()

                studentList.add(Student(id, name, age, major, gpa))
                println("-> Da them sinh vien thanh cong!")
            }
            2 -> {
                println("\n--- DANH SACH TAT CA SINH VIEN ---")
                if (studentList.isEmpty()) {
                    println("Danh sach trong!")
                } else {
                    studentList.forEach {
                        println("ID: ${it.studentID} | Name: ${it.fullName} | Age: ${it.age} | Major: ${it.major} | GPA: ${it.gpa}")
                    }
                }
            }
            3 -> {
                print("Nhap ten hoac ID sinh vien can tim: ")
                val keyword = scanner.nextLine().lowercase()
                val results = studentList.filter { it.fullName.lowercase().contains(keyword) || it.studentID.lowercase().contains(keyword) }
                if (results.isEmpty()) {
                    println("Khong tim thay sinh vien phu hop!")
                } else {
                    println("--- KET QUA TIM KIEM ---")
                    results.forEach { println("ID: ${it.studentID} | Name: ${it.fullName} | Major: ${it.major} | GPA: ${it.gpa}") }
                }
            }
            4 -> {
                if (studentList.isEmpty()) println("Danh sach trong!")
                else {
                    val avg = studentList.map { it.gpa }.average()
                    println("-> GPA trung binh cua tat ca sinh vien: %.2f".format(avg))
                }
            }
            5 -> {
                val best = studentList.maxByOrNull { it.gpa }
                if (best != null) {
                    println("-> Sinh vien co GPA cao nhat: ${best.fullName} (GPA: ${best.gpa})")
                } else {
                    println("Danh sach trong!")
                }
            }
            6 -> {
                print("Nhap Student ID cua sinh vien can xoa: ")
                val idDel = scanner.nextLine()
                val removed = studentList.removeIf { it.studentID.equals(idDel, ignoreCase = true) }
                if (removed) println("-> Da xoa thanh cong!")
                else println("-> Khong tim thay ID phu hop de xoa!")
            }
            7 -> {
                // Thich ung voi ca he 10 (>=8.0) hoac he 4 (>=3.2)
                val count = studentList.count { it.gpa >= 8.0 || it.gpa >= 3.2 }
                println("-> So sinh vien co GPA cao (>= 8.0 hoac >= 3.2): $count")
            }
            8 -> {
                val count = studentList.count { it.gpa < 5.0 }
                println("-> So sinh vien co GPA < 5.0 la: $count")
            }
            9 -> {
                print("Nhap ten nganh can tinh GPA trung binh (VD: Dieu duong): ")
                val m = scanner.nextLine()
                val filtered = studentList.filter { it.major.equals(m, ignoreCase = true) }
                if (filtered.isNotEmpty()) {
                    val avg = filtered.map { it.gpa }.average()
                    println("-> GPA trung binh nganh $m la: %.2f".format(avg))
                } else {
                    println("-> Khong co sinh vien nao trong nganh nay!")
                }
            }
            10 -> {
                val best = studentList.maxByOrNull { it.gpa }
                if (best != null) {
                    println("-> Sinh vien co GPA cao nhat: ${best.fullName} - ${best.major} (GPA: ${best.gpa})")
                }
            }
            11 -> {
                val oldest = studentList.maxByOrNull { it.age }
                if (oldest != null) {
                    println("-> Sinh vien lon tuoi nhat: ${oldest.fullName} (${oldest.age} tuoi)")
                }
            }
            12 -> {
                // Khoang gia tri flexible cho ca he 10 va he 4
                val listRange = studentList.filter { it.gpa in 7.0..8.5 || it.gpa in 3.4..4.0 }
                println("--- SINH VIEN CO GPA NAM TRONG KHOANG GIA TRI ---")
                listRange.forEach { println("${it.fullName} - GPA: ${it.gpa}") }
            }
            13 -> {
                print("Nhap ten nganh: ")
                val m = scanner.nextLine()
                val listMajor = studentList.filter { it.major.equals(m, ignoreCase = true) }
                println("--- DANH SACH THUOC NGANH $m ---")
                if (listMajor.isEmpty()) println("Khong co sinh vien nao!")
                else listMajor.forEach { println("${it.fullName} - ID: ${it.studentID} - GPA: ${it.gpa}") }
            }
            14 -> {
                print("Nhap mot phan ten can tim (VD: Hien, Nhat, Nhan...): ")
                val partName = scanner.nextLine().lowercase()
                val listPart = studentList.filter { it.fullName.lowercase().contains(partName) }
                println("--- KET QUA TIM PHAN TEN ---")
                if (listPart.isEmpty()) println("Khong tim thay!")
                else listPart.forEach { println("${it.fullName} - ID: ${it.studentID}") }
            }
            15 -> {
                val sortedGpa = studentList.sortedByDescending { it.gpa }
                println("--- SAP XEP SINH VIEN THEO GPA GIAM DAN ---")
                sortedGpa.forEach { println("${it.fullName} - GPA: ${it.gpa}") }
            }
            16 -> {
                val top3 = studentList.sortedByDescending { it.gpa }.take(3)
                println("--- TOP 3 SINH VIEN GPA CAO NHAT ---")
                top3.forEach { println("${it.fullName} - GPA: ${it.gpa}") }
            }
            17 -> {
                val sortedAge = studentList.sortedBy { it.age }
                println("--- SAP XEP SINH VIEN THEO TUOI ---")
                sortedAge.forEach { println("${it.fullName} - ${it.age} tuoi") }
            }
            18 -> {
                val sortedName = studentList.sortedBy { it.fullName }
                println("--- SAP XEP SINH VIEN THEO TEN (ALPHABET) ---")
                sortedName.forEach { println("${it.fullName} - ID: ${it.studentID}") }
            }
            0 -> println("Thoat chuong trinh. Tam biet!")
            else -> println("Lua chon khong hop le, vui long chon lai!")
        }
    } while (choice != 0)
}