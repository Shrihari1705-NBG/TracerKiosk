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
            nodeId = "N1",
            aliases = listOf("plasin", "hod")
        ),

        Destination(
            id = "faculty_mahendra",
            name = "Dr. Mahendra M Dixit",
            category = DestinationCategory.FACULTY,
            nodeId = "N20",
            aliases = listOf("mahendra", "dixit")
        ),

        Destination(
            id = "faculty_kolaki",
            name = "Mr. A V Kolaki",
            category = DestinationCategory.FACULTY,
            nodeId = "N23",
            aliases = listOf("kolaki")
        ),

        Destination(
            id = "faculty_nikhil",
            name = "Mr. Nikhil A Kulkarni",
            category = DestinationCategory.FACULTY,
            nodeId = "N9",
            aliases = listOf("nikhil", "kulkarni")
        ),

        Destination(
            id = "faculty_raghavendra",
            name = "Mr. Raghavendra Nagaralli",
            category = DestinationCategory.FACULTY,
            nodeId = "N8",
            aliases = listOf("raghavendra", "nagaralli")
        ),

        Destination(
            id = "faculty_rohini",
            name = "Mrs. Rohini Kallur",
            category = DestinationCategory.FACULTY,
            nodeId = "N16",
            aliases = listOf("rohini", "kallur")
        ),

        Destination(
            id = "faculty_suraj",
            name = "Mr. Suraj Kadli",
            category = DestinationCategory.FACULTY,
            nodeId = "N9",
            aliases = listOf("suraj", "kadli")
        ),

        Destination(
            id = "faculty_vijayalaxmi",
            name = "Mrs. Vijayalaxmi C Kalal",
            category = DestinationCategory.FACULTY,
            nodeId = "N17",
            aliases = listOf("vijayalaxmi", "kalal")
        ),

        Destination(
            id = "faculty_pooja",
            name = "Ms. Pooja C Shidhe",
            category = DestinationCategory.FACULTY,
            nodeId = "N7",
            aliases = listOf("pooja", "shidhe")
        ),

        Destination(
            id = "faculty_sudheendra",
            name = "Mr. Sudheendra Yalagur",
            category = DestinationCategory.FACULTY,
            nodeId = "N8",
            aliases = listOf("sudheendra", "yalagur")
        ),

        Destination(
            id = "faculty_pavitra",
            name = "Ms. Pavitra M Badiger",
            category = DestinationCategory.FACULTY,
            nodeId = "N24",
            aliases = listOf("pavitra", "badiger")
        ),

        Destination(
            id = "faculty_ashwini",
            name = "Mrs. Ashwini Garaddi",
            category = DestinationCategory.FACULTY,
            nodeId = "N7",
            aliases = listOf("ashwini", "garaddi")
        ),

        Destination(
            id = "faculty_rajeshwari",
            name = "Mrs. Rajeshwari Pashupatimath",
            category = DestinationCategory.FACULTY,
            nodeId = "N25",
            aliases = listOf("rajeshwari", "pashupatimath")
        ),

        Destination(
            id = "faculty_jyothi",
            name = "Mrs. Jyothi Kammar",
            category = DestinationCategory.FACULTY,
            nodeId = "N24",
            aliases = listOf("jyothi", "kammar")
        ),

        Destination(
            id = "faculty_meenal",
            name = "Dr. Meenal M Kaliwal",
            category = DestinationCategory.FACULTY,
            nodeId = "N26",
            aliases = listOf("meenal", "kaliwal")
        ),

        Destination(
            id = "faculty_gururaj",
            name = "Dr. Gururaj Hatti",
            category = DestinationCategory.FACULTY,
            nodeId = "N19",
            aliases = listOf("gururaj", "hatti")
        ),

        // ==========================================================
        // Laboratories
        // ==========================================================

        Destination(
            id = "analog_lab",
            name = "Analog Lab",
            category = DestinationCategory.LABORATORY,
            nodeId = "N3",
            aliases = listOf("analog")
        ),

        Destination(
            id = "dsp_lab",
            name = "DSP Lab",
            category = DestinationCategory.LABORATORY,
            nodeId = "N4",
            aliases = listOf("dsp", "digital signal processing")
        ),

        Destination(
            id = "dc_lab",
            name = "DC Lab",
            category = DestinationCategory.LABORATORY,
            nodeId = "N18",
            aliases = listOf("dc")
        ),

        Destination(
            id = "research_lab",
            name = "Research Lab",
            category = DestinationCategory.LABORATORY,
            nodeId = "N6",
            aliases = listOf("research")
        ),

        // ==========================================================
        // Classrooms
        // ==========================================================

        Destination(
            id = "block6_a",
            name = "Block 6(A)",
            category = DestinationCategory.CLASSROOM,
            nodeId = "N27",
            aliases = listOf("6a", "block 6")
        ),

        Destination(
            id = "block6_smart",
            name = "Block 6 (Smart Room)",
            category = DestinationCategory.CLASSROOM,
            nodeId = "N21",
            aliases = listOf("smart", "smart room")
        ),

        // ==========================================================
        // Offices
        // ==========================================================

        Destination(
            id = "hod_ece",
            name = "HOD ECE",
            category = DestinationCategory.OFFICE,
            nodeId = "N1",
            aliases = listOf("hod", "head of department")
        ),

        // ==========================================================
        // Facilities
        // ==========================================================

        Destination(
            id = "department_library",
            name = "Department Library",
            category = DestinationCategory.FACILITY,
            nodeId = "N5",
            aliases = listOf("library")
        ),

        Destination(
            id = "ladies_room",
            name = "Ladies Room",
            category = DestinationCategory.FACILITY,
            nodeId = "N15",
            aliases = listOf("girls room")
        ),

        Destination(
            id = "ladies_washroom",
            name = "Ladies Washroom",
            category = DestinationCategory.FACILITY,
            nodeId = "N11",
            aliases = listOf("washroom", "restroom", "toilet")
        )

    )

}