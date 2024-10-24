package com.nexu.android.core.designsystem.component

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * nexu navigation bar item with icon and label content slots. Wraps Material 3
 * [NavigationBarItem].
 *
 * @param selected Whether this item is selected.
 * @param onClick The callback to be invoked when this item is selected.
 * @param icon The item icon content.
 * @param modifier Modifier to be applied to this item.
 * @param selectedIcon The item icon content when selected.
 * @param enabled controls the enabled state of this item. When `false`, this item will not be
 * clickable and will appear disabled to accessibility services.
 * @param label The item text label content.
 * @param alwaysShowLabel Whether to always show the label for this item. If false, the label will
 * only be shown when this item is selected.
 */
@Composable
fun RowScope.NexuNavigationBarItem(
    selected: Boolean,
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    selectedIcon: @Composable () -> Unit = icon,
    enabled: Boolean = true,
    label: @Composable (() -> Unit)? = null,
    alwaysShowLabel: Boolean = true
) {
    NavigationBarItem(
        selected = selected,
        onClick = onClick,
        icon = if (selected) selectedIcon else icon,
        modifier = modifier,
        enabled = enabled,
        label = label,
        alwaysShowLabel = alwaysShowLabel,
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = NexuNavigationDefaults.navigationSelectedItemColor(),
            unselectedIconColor = NexuNavigationDefaults.navigationContentColor(),
            selectedTextColor = NexuNavigationDefaults.navigationSelectedTextColor(),
            unselectedTextColor = NexuNavigationDefaults.navigationContentColor(),
            indicatorColor = NexuNavigationDefaults.navigationIndicatorColor()
        )
    )
}

/**
 * Nexu navigation bar with content slot. Wraps Material 3 [NavigationBar].
 *
 * @param modifier Modifier to be applied to the navigation bar.
 * @param content Destinations inside the navigation bar. This should contain multiple
 * [NavigationBarItem]s.
 */
@Composable
fun NexuNavigationBar(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {
    NavigationBar(
        modifier = modifier,
        containerColor = NexuNavigationDefaults.NavigationContainerColor,
        contentColor = NexuNavigationDefaults.navigationContentColor(),
        tonalElevation = 0.dp,
        content = content
    )
}

/**
 * Nexu navigation rail item with icon and label content slots. Wraps Material 3
 * [NavigationRailItem].
 *
 * @param selected Whether this item is selected.
 * @param onClick The callback to be invoked when this item is selected.
 * @param icon The item icon content.
 * @param modifier Modifier to be applied to this item.
 * @param selectedIcon The item icon content when selected.
 * @param enabled controls the enabled state of this item. When `false`, this item will not be
 * clickable and will appear disabled to accessibility services.
 * @param label The item text label content.
 * @param alwaysShowLabel Whether to always show the label for this item. If false, the label will
 * only be shown when this item is selected.
 */
@Composable
fun NexuNavigationRailItem(
    selected: Boolean,
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    selectedIcon: @Composable () -> Unit = icon,
    enabled: Boolean = true,
    label: @Composable (() -> Unit)? = null,
    alwaysShowLabel: Boolean = true
) {
    NavigationRailItem(
        selected = selected,
        onClick = onClick,
        icon = if (selected) selectedIcon else icon,
        modifier = modifier,
        enabled = enabled,
        label = label,
        alwaysShowLabel = alwaysShowLabel,
        colors = NavigationRailItemDefaults.colors(
            selectedIconColor = NexuNavigationDefaults.navigationSelectedItemColor(),
            unselectedIconColor = NexuNavigationDefaults.navigationContentColor(),
            selectedTextColor = NexuNavigationDefaults.navigationSelectedTextColor(),
            unselectedTextColor = NexuNavigationDefaults.navigationContentColor(),
            indicatorColor = NexuNavigationDefaults.navigationIndicatorColor()
        )
    )
}

/**
 * Nexu navigation rail with header and content slots. Wraps Material 3 [NavigationRail].
 *
 * @param modifier Modifier to be applied to the navigation rail.
 * @param header Optional header that may hold a floating action button or a logo.
 * @param content Destinations inside the navigation rail. This should contain multiple
 * [NavigationRailItem]s.
 */
@Composable
fun NexuNavigationRail(
    modifier: Modifier = Modifier,
    header: @Composable (ColumnScope.() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    NavigationRail(
        modifier = modifier,
        containerColor = NexuNavigationDefaults.NavigationContainerColor,
        contentColor = NexuNavigationDefaults.navigationContentColor(),
        header = header,
        content = content
    )
}

/**
 * Nexu navigation default values.
 */
object NexuNavigationDefaults {
    val NavigationContainerColor = Color.Transparent

    @Composable
    fun navigationContentColor() = MaterialTheme.colorScheme.onBackground

    @Composable
    fun navigationSelectedItemColor() = MaterialTheme.colorScheme.onTertiaryContainer

    @Composable
    fun navigationSelectedTextColor() = MaterialTheme.colorScheme.tertiaryContainer

    @Composable
    fun navigationIndicatorColor() = MaterialTheme.colorScheme.tertiaryContainer
}
