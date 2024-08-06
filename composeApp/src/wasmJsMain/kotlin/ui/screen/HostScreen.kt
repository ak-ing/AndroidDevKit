package ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Host页面
 * @author Ak
 * 2024/8/5 10:29
 */

const val HostRouter = "HostRouter"

/**
 * 功能入口
 */
data class Function(val title: String, val description: String, val route: String)

private val functions = listOf(
    Function("批量重命名", "将文件名称批量修改为迭代数字的格式", ""),
    Function("Fast frame animation", "输入图片，快速生成帧动画文件", ""),
    Function("Fast selector", "输入图片，生成Android selector背景文件", ""),
    Function("批量重命名1", "将文件名称批量修改为迭代数字的格式", ""),
    Function("批量重命名2", "将文件名称批量修改为迭代数字的格式", "")
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HostScreen(onNavigate: (String) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 180.dp),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(functions, key = { it.title }) {
            FunctionCard(function = it, modifier = Modifier.animateItemPlacement()) {
                onNavigate(it.route)
            }
        }
    }
}

/**
 * 功能卡片
 */
@Composable
fun FunctionCard(function: Function, modifier: Modifier = Modifier, onClick: () -> Unit) {
    ElevatedCard(
        modifier = modifier.size(180.dp, 220.dp)
    ) {
        Column(Modifier.padding(16.dp)) {
            Text(text = function.title, style = MaterialTheme.typography.titleLarge)
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = function.description,
                style = MaterialTheme.typography.bodyMedium
            )
            Box(modifier = Modifier.fillMaxSize()) {
                Button(
                    modifier = Modifier.align(Alignment.BottomEnd),
                    onClick = onClick
                ) {
                    Text(text = "Get started")
                }
            }
        }
    }
}