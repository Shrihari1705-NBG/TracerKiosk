package com.tracer.kiosk.presentation.feature.faculty

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.tracer.kiosk.presentation.components.faculty.FacultyCard
import com.tracer.kiosk.presentation.components.faculty.HodCard
import com.tracer.kiosk.presentation.components.sidebar.Sidebar
import com.tracer.kiosk.presentation.navigation.Screen
import com.tracer.kiosk.R
import com.tracer.kiosk.presentation.components.common.TracerBackground

@Composable
fun FacultyScreen(
    navController: NavHostController
) {

    TracerBackground {

        Row(
            modifier = Modifier.fillMaxSize()
        ) {

            // Sidebar
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(260.dp)
            ) {

                Sidebar(
                    selectedRoute = Screen.Faculty.route,

                    onHomeClick = {
                        navController.navigate(Screen.Home.route)
                    },

                    onNavigateClick = {
                        navController.navigate(Screen.Navigation.route)
                    },

                    onFacultyClick = {},

                    onDepartmentClick = {
                        navController.navigate(Screen.Department.route)
                    },

                    onAboutClick = {
                        navController.navigate(Screen.About.route)
                    }
                )

            }

            // Main Content
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .verticalScroll(rememberScrollState())
                    .padding(32.dp)
            ){

                Text(
                    text = "Meet the 15 Faculty Members of the E&CE Department",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(32.dp))

                HodCard()

                Spacer(modifier = Modifier.height(36.dp))

// Row 1
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(24.dp)
                ) {

                    FacultyCard(
                        photo = R.drawable.faculty_mahendra_dixit,
                        name = "Dr. Mahendra M Dixit",
                        designation = "Professor & Dean",
                        modifier = Modifier.weight(1f)
                    )

                    FacultyCard(
                        photo = R.drawable.faculty_allamprabhu_kolaki,
                        name = "Mr. A V Kolaki",
                        designation = "Assistant Professor",
                        modifier = Modifier.weight(1f)
                    )

                }

                Spacer(modifier = Modifier.height(24.dp))

// Row 2
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(24.dp)
                ) {

                    FacultyCard(
                        photo = R.drawable.faculty_nikhil_kulkarni,
                        name = "Mr. Nikhil A. Kulkarni",
                        designation = "Assistant Professor",
                        modifier = Modifier.weight(1f)
                    )

                    FacultyCard(
                        photo = R.drawable.faculty_raghavendra_nagaralli,
                        name = "Mr. Raghavendra Nagaralli",
                        designation = "Assistant Professor",
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

// Row 3
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(24.dp)
                ) {

                    FacultyCard(
                        photo = R.drawable.faculty_rohini_kallur,
                        name = "Mrs. Rohini Kallur",
                        designation = "Assistant Professor",
                        modifier = Modifier.weight(1f)
                    )

                    FacultyCard(
                        photo = R.drawable.faculty_suraj_kadli,
                        name = "Mr. Suraj Kadli",
                        designation = "Assistant Professor",
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))
//Row 4
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(24.dp)
                ) {

                    FacultyCard(
                        photo = R.drawable.faculty_deepak_sharma,
                        name = "Mr. Deepak Sharma",
                        designation = "Assistant Professor",
                        modifier = Modifier.weight(1f)
                    )

                    FacultyCard(
                        photo = R.drawable.faculty_vijayalaxmi_kalal,
                        name = "Mrs. Vijayalaxmi C. Kalal",
                        designation = "Assistant Professor",
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

//Row 5
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(24.dp)
                ) {

                    FacultyCard(
                        photo = R.drawable.faculty_pooja_shinde,
                        name = "Ms. Pooja C Shindhe",
                        designation = "Assistant Professor",
                        modifier = Modifier.weight(1f)
                    )

                    FacultyCard(
                        photo = R.drawable.faculty_sudhindra_yalagur,
                        name = "Mr. Sudheendra Yalagur",
                        designation = "Assistant Professor",
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

//Row 6
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(24.dp)
                ) {

                    FacultyCard(
                        photo = R.drawable.faculty_pavitra_badiger,
                        name = "Ms. Pavitra M Badiger",
                        designation = "Assistant Professor",
                        modifier = Modifier.weight(1f)
                    )

                    FacultyCard(
                        photo = R.drawable.faculty_ashwini_garaddi,
                        name = "Mrs. Ashwini Garaddi",
                        designation = "Assistant Professor",
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

//Row 7
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(24.dp)
                ) {

                    FacultyCard(
                        photo = R.drawable.faculty_rajeshwari_pashupatimath,
                        name = "Mrs. Rajeshwari Pashupatimath",
                        designation = "Assistant Professor",
                        modifier = Modifier.weight(1f)
                    )

                    FacultyCard(
                        photo = R.drawable.faculty_jyothi_kammar,
                        name = "Mrs. Jyoti Kammar",
                        designation = "Assistant Professor",
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}