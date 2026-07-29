package com.tracer.kiosk.presentation.feature.navigation.data

import com.tracer.kiosk.presentation.feature.navigation.model.Destination
import com.tracer.kiosk.presentation.feature.navigation.model.DestinationCategory

object DestinationRepository {

    val destinations = listOf(

        // ==========================================================
        // Faculty & Staff
        // ==========================================================

        Destination(
            id = "faculty_plasin",
            name = "Prof. Plasin F Dias",
            category = DestinationCategory.FACULTY,
            nodeId = "faculty_plasin",
            aliases = listOf("plasin", "hod")
        ),

        Destination(
            id = "faculty_mahendra",
            name = "Dr. Mahendra M Dixit",
            category = DestinationCategory.FACULTY,
            nodeId = "faculty_mahendra",
            aliases = listOf("mahendra", "dixit")
        ),

        Destination(
            id = "faculty_kolaki",
            name = "Mr. A V Kolaki",
            category = DestinationCategory.FACULTY,
            nodeId = "faculty_kolaki",
            aliases = listOf("kolaki")
        ),

        Destination(
            id = "faculty_nikhil",
            name = "Mr. Nikhil A Kulkarni",
            category = DestinationCategory.FACULTY,
            nodeId = "faculty_nikhil",
            aliases = listOf("nikhil", "kulkarni")
        ),

        Destination(
            id = "faculty_raghavendra",
            name = "Mr. Raghavendra Nagaralli",
            category = DestinationCategory.FACULTY,
            nodeId = "faculty_raghavendra",
            aliases = listOf("raghavendra", "nagaralli")
        ),

        Destination(
            id = "faculty_rohini",
            name = "Mrs. Rohini Kallur",
            category = DestinationCategory.FACULTY,
            nodeId = "faculty_rohini",
            aliases = listOf("rohini", "kallur")
        ),

        Destination(
            id = "faculty_suraj",
            name = "Mr. Suraj Kadli",
            category = DestinationCategory.FACULTY,
            nodeId = "faculty_suraj",
            aliases = listOf("suraj", "kadli")
        ),

        Destination(
            id = "faculty_vijayalaxmi",
            name = "Mrs. Vijayalaxmi C Kalal",
            category = DestinationCategory.FACULTY,
            nodeId = "faculty_vijayalaxmi",
            aliases = listOf("vijayalaxmi", "kalal")
        ),

        Destination(
            id = "faculty_pooja",
            name = "Ms. Pooja C Shidhe",
            category = DestinationCategory.FACULTY,
            nodeId = "faculty_pooja",
            aliases = listOf("pooja", "shidhe")
        ),

        Destination(
            id = "faculty_sudheendra",
            name = "Mr. Sudheendra Yalagur",
            category = DestinationCategory.FACULTY,
            nodeId = "faculty_sudheendra",
            aliases = listOf("sudheendra", "yalagur")
        ),

        Destination(
            id = "faculty_pavitra",
            name = "Ms. Pavitra M Badiger",
            category = DestinationCategory.FACULTY,
            nodeId = "faculty_pavitra",
            aliases = listOf("pavitra", "badiger")
        ),

        Destination(
            id = "faculty_ashwini",
            name = "Mrs. Ashwini Garaddi",
            category = DestinationCategory.FACULTY,
            nodeId = "faculty_ashwini",
            aliases = listOf("ashwini", "garaddi")
        ),

        Destination(
            id = "faculty_rajeshwari",
            name = "Mrs. Rajeshwari Pashupatimath",
            category = DestinationCategory.FACULTY,
            nodeId = "faculty_rajeshwari",
            aliases = listOf("rajeshwari", "pashupatimath")
        ),

        Destination(
            id = "faculty_jyothi",
            name = "Mrs. Jyothi Kammar",
            category = DestinationCategory.FACULTY,
            nodeId = "faculty_jyothi",
            aliases = listOf("jyothi", "kammar")
        ),

        Destination(
            id = "faculty_meenal",
            name = "Dr. Meenal M Kaliwal",
            category = DestinationCategory.FACULTY,
            nodeId = "faculty_meenal",
            aliases = listOf("meenal", "kaliwal")
        ),

        Destination(
            id = "faculty_gururaj",
            name = "Dr. Gururaj Hatti",
            category = DestinationCategory.FACULTY,
            nodeId = "faculty_gururaj",
            aliases = listOf("gururaj", "hatti")
        ),

        // ==========================================================
        // Laboratories
        // ==========================================================

        Destination(
            id = "analog_lab",
            name = "Analog Lab",
            category = DestinationCategory.LABORATORY,
            nodeId = "analog_lab",
            aliases = listOf("analog")
        ),

        Destination(
            id = "dsp_lab",
            name = "DSP Lab",
            category = DestinationCategory.LABORATORY,
            nodeId = "dsp_lab",
            aliases = listOf("dsp", "digital signal processing")
        ),

        Destination(
            id = "dc_lab",
            name = "DC Lab",
            category = DestinationCategory.LABORATORY,
            nodeId = "dc_lab",
            aliases = listOf("dc")
        ),

        Destination(
            id = "research_lab",
            name = "Research Lab",
            category = DestinationCategory.LABORATORY,
            nodeId = "research_lab",
            aliases = listOf("research")
        ),

        // ==========================================================
        // Classrooms
        // ==========================================================

        Destination(
            id = "block6_a",
            name = "Block 6(A)",
            category = DestinationCategory.CLASSROOM,
            nodeId = "block6_a",
            aliases = listOf("6a", "block 6")
        ),

        Destination(
            id = "block6_smart",
            name = "Block 6 (Smart Room)",
            category = DestinationCategory.CLASSROOM,
            nodeId = "block6_smart",
            aliases = listOf("smart", "smart room")
        ),

        // ==========================================================
        // Offices
        // ==========================================================

        Destination(
            id = "hod_ece",
            name = "HOD ECE",
            category = DestinationCategory.OFFICE,
            nodeId = "hod_ece",
            aliases = listOf("hod", "head of department")
        ),

        // ==========================================================
        // Facilities
        // ==========================================================

        Destination(
            id = "department_library",
            name = "Department Library",
            category = DestinationCategory.FACILITY,
            nodeId = "department_library",
            aliases = listOf("library")
        ),

        Destination(
            id = "ladies_room",
            name = "Ladies Room",
            category = DestinationCategory.FACILITY,
            nodeId = "ladies_room",
            aliases = listOf("girls room")
        ),

        Destination(
            id = "ladies_washroom",
            name = "Ladies Washroom",
            category = DestinationCategory.FACILITY,
            nodeId = "ladies_washroom",
            aliases = listOf("washroom", "restroom", "toilet")
        )

    )

}