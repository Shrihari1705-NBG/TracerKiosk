
package com.tracer.kiosk.presentation.feature.faculty

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.tracer.kiosk.R
import com.tracer.kiosk.presentation.components.common.TracerBackground
import com.tracer.kiosk.presentation.components.faculty.FacultyCard
import com.tracer.kiosk.presentation.components.faculty.HodCard
import com.tracer.kiosk.presentation.components.sidebar.Sidebar
import com.tracer.kiosk.presentation.navigation.Screen

@Composable
fun FacultyScreen(
    navController: NavHostController
) {
    val assistantProfessor =
        stringResource(R.string.faculty_designation_assistant_professor)

    TracerBackground {
        Row(
            modifier = Modifier.fillMaxSize()
        ) {
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

            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .verticalScroll(rememberScrollState())
                    .padding(32.dp)
            ) {
                Text(
                    text = stringResource(R.string.faculty_screen_title),
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(32.dp))

                HodCard(
                    heading = stringResource(R.string.hod_heading),
                    designation = stringResource(R.string.hod_designation),
                    experience = stringResource(
                        R.string.hod_experience,
                        26
                    ),
                    publications = stringResource(
                        R.string.hod_publications,
                        18
                    )
                )

                Spacer(modifier = Modifier.height(36.dp))

                // Row 1
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    FacultyCard(
                        photo = R.drawable.faculty_mahendra_dixit,
                        name = "Dr. Mahendra M Dixit",
                        designation = stringResource(
                            R.string.faculty_designation_professor_dean
                        ),
                        modifier = Modifier.weight(1f)
                    )

                    FacultyCard(
                        photo = R.drawable.faculty_allamprabhu_kolaki,
                        name = "Mr. A V Kolaki",
                        designation = assistantProfessor,
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
                        designation = assistantProfessor,
                        modifier = Modifier.weight(1f)
                    )

                    FacultyCard(
                        photo = R.drawable.faculty_raghavendra_nagaralli,
                        name = "Mr. Raghavendra Nagaralli",
                        designation = assistantProfessor,
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
                        designation = assistantProfessor,
                        modifier = Modifier.weight(1f)
                    )

                    FacultyCard(
                        photo = R.drawable.faculty_suraj_kadli,
                        name = "Mr. Suraj Kadli",
                        designation = assistantProfessor,
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Row 4
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    FacultyCard(
                        photo = R.drawable.faculty_deepak_sharma,
                        name = "Mr. Deepak Sharma",
                        designation = assistantProfessor,
                        modifier = Modifier.weight(1f)
                    )

                    FacultyCard(
                        photo = R.drawable.faculty_vijayalaxmi_kalal,
                        name = "Mrs. Vijayalaxmi C. Kalal",
                        designation = assistantProfessor,
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Row 5
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    FacultyCard(
                        photo = R.drawable.faculty_pooja_shinde,
                        name = "Ms. Pooja C Shindhe",
                        designation = assistantProfessor,
                        modifier = Modifier.weight(1f)
                    )

                    FacultyCard(
                        photo = R.drawable.faculty_sudhindra_yalagur,
                        name = "Mr. Sudheendra Yalagur",
                        designation = assistantProfessor,
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Row 6
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    FacultyCard(
                        photo = R.drawable.faculty_pavitra_badiger,
                        name = "Ms. Pavitra M Badiger",
                        designation = assistantProfessor,
                        modifier = Modifier.weight(1f)
                    )

                    FacultyCard(
                        photo = R.drawable.faculty_ashwini_garaddi,
                        name = "Mrs. Ashwini Garaddi",
                        designation = assistantProfessor,
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Row 7
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    FacultyCard(
                        photo = R.drawable.faculty_rajeshwari_pashupatimath,
                        name = "Mrs. Rajeshwari Pashupatimath",
                        designation = assistantProfessor,
                        modifier = Modifier.weight(1f)
                    )

                    FacultyCard(
                        photo = R.drawable.faculty_jyothi_kammar,
                        name = "Mrs. Jyoti Kammar",
                        designation = assistantProfessor,
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}
