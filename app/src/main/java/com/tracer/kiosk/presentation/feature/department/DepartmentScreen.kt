
package com.tracer.kiosk.presentation.feature.department

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.tracer.kiosk.R
import com.tracer.kiosk.presentation.components.common.TracerBackground
import com.tracer.kiosk.presentation.components.department.DepartmentHeaderCard
import com.tracer.kiosk.presentation.components.department.DepartmentOverviewCard
import com.tracer.kiosk.presentation.components.department.DepartmentStatCard
import com.tracer.kiosk.presentation.components.department.MissionCard
import com.tracer.kiosk.presentation.components.department.VisionCard
import com.tracer.kiosk.presentation.components.sidebar.Sidebar
import com.tracer.kiosk.presentation.components.topbar.AppHeader
import com.tracer.kiosk.presentation.navigation.Screen

@Composable
fun DepartmentScreen(
    navController: NavHostController
) {
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
                    selectedRoute = Screen.Department.route,
                    onHomeClick = {
                        navController.navigate(Screen.Home.route)
                    },
                    onNavigateClick = {
                        navController.navigate(Screen.Navigation.route)
                    },
                    onFacultyClick = {
                        navController.navigate(Screen.Faculty.route)
                    },
                    onDepartmentClick = {},
                    onAboutClick = {
                        navController.navigate(Screen.About.route)
                    }
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        start = 32.dp,
                        top = 56.dp,
                        end = 32.dp,
                        bottom = 32.dp
                    )
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                AppHeader()

                DepartmentHeaderCard()

                DepartmentOverviewCard()

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    DepartmentStatCard(
                        value = "15",
                        label = stringResource(
                            R.string.department_stat_faculty
                        ),
                        modifier = Modifier.weight(1f)
                    )

                    DepartmentStatCard(
                        value = "03",
                        label = stringResource(
                            R.string.department_stat_phd
                        ),
                        modifier = Modifier.weight(1f)
                    )

                    DepartmentStatCard(
                        value = "10",
                        label = stringResource(
                            R.string.department_stat_pursuing_phd
                        ),
                        modifier = Modifier.weight(1f)
                    )

                    DepartmentStatCard(
                        value = "07",
                        label = stringResource(
                            R.string.department_stat_labs
                        ),
                        modifier = Modifier.weight(1f)
                    )
                }

                VisionCard()

                MissionCard()

                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}
