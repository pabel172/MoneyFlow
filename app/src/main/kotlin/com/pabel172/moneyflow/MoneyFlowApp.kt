package com.pabel172.moneyflow

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme as LegacyMaterialTheme
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.rememberDismissState
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton as MaterialIconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.material3.rememberScaffoldState
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults as NavDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberTopAppBarScrollState
n
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.StrokeWidth
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.BusinessCenter
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ReceiptLong
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Savings
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material.icons.filled.Wallet
import androidx.compose.material.icons.filled.Work
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.LightMode
import androidx.compose.material3.MaterialTheme as M3Theme
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.PopupProperties
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun MoneyFlowApp() {
    var selectedTab by rememberSaveable { mutableStateOf(BottomScreen.Home) }
    var isDarkMode by rememberSaveable { mutableStateOf(false) }
    var userName by rememberSaveable { mutableStateOf("Shahriar") }
    var currency by rememberSaveable { mutableStateOf("BDT") }
    var filterQuery by rememberSaveable { mutableStateOf("") }
    var currentCategoryId by rememberSaveable { mutableStateOf<String?>(null) }

    val expenseCategories = remember {
        mutableStateListOf(
            CategoryItem("grocery", "Grocery", Icons.Default.ShoppingBag, Color(0xFF94DAB2), Color(0xFF1F7A45), TransactionType.EXPENSE),
            CategoryItem("shopping", "Shopping", Icons.Default.CreditCard, Color(0xFFB5C6F4), Color(0xFF26439B), TransactionType.EXPENSE),
            CategoryItem("tea", "Tea Time", Icons.Default.Savings, Color(0xFFF7D7AC), Color(0xFF8A5E2F), TransactionType.EXPENSE)
        )
    }

    val incomeCategories = remember {
        mutableStateListOf(
            CategoryItem("salary", "Salary", Icons.Default.Work, Color(0xFFB7F8E0), Color(0xFF0E7C63), TransactionType.INCOME),
            CategoryItem("bonus", "Bonus", Icons.Default.Savings, Color(0xFFFAC2E5), Color(0xFF8D2B6B), TransactionType.INCOME)
        )
    }

    val transactions = remember {
        mutableStateListOf(
            MoneyTransaction("txn-1", "Grocery at Urban Mart", expenseCategories[0], "450", "Weekly essentials", "Apr 23", TransactionType.EXPENSE),
            MoneyTransaction("txn-2", "Salary payment", incomeCategories[0], "125000", "April salary", "Apr 28", TransactionType.INCOME),
            MoneyTransaction("txn-3", "Bonus reward", incomeCategories[1], "15000", "Project bonus", "Apr 30", TransactionType.INCOME),
            MoneyTransaction("txn-4", "Shopping spree", expenseCategories[1], "7200", "New jacket", "May 04", TransactionType.EXPENSE),
            MoneyTransaction("txn-5", "Tea latte", expenseCategories[2], "180", "Afternoon break", "May 09", TransactionType.EXPENSE)
        )
    }

    MoneyFlowTheme(darkTheme = isDarkMode) {
        Surface(modifier = Modifier.fillMaxSize()) {
            Scaffold(
                bottomBar = {
                    BottomNavigationBar(selectedScreen) { selectedTab = it }
                },
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = { currentCategoryId = selectedTab.name.lowercase() },
                        containerColor = MaterialTheme.colorScheme.primary,
                        shape = CircleShape
                    ) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
                    }
                },
                floatingActionButtonPosition = androidx.compose.material3.FabPosition.Center,
                content = { padding ->
                    Box(modifier = Modifier.padding(padding)) {
                        Crossfade(targetState = selectedTab) { screen ->
                            when (screen) {
                                BottomScreen.Home -> HomeScreen(
                                    userName = userName,
                                    currency = currency,
                                    transactions = transactions,
                                    expenseCategories = expenseCategories,
                                    incomeCategories = incomeCategories,
                                    filterQuery = filterQuery,
                                    onQueryChange = { filterQuery = it },
                                    onDeleteTransaction = { transactions.remove(it) })
                                BottomScreen.Expense -> ExpenseScreen(
                                    currency = currency,
                                    categories = expenseCategories,
                                    transactions = transactions.filter { it.type == TransactionType.EXPENSE },
                                    onAddCategory = { expenseCategories.add(it) },
                                    onEditCategory = { updated ->
                                        expenseCategories.indexOfFirst { it.id == updated.id }.takeIf { it >= 0 }?.let { index ->
                                            expenseCategories[index] = updated
                                        }
                                    },
                                    onDeleteCategory = { cat ->
                                        expenseCategories.remove(cat)
                                        transactions.removeAll { it.category.id == cat.id }
                                    },
                                    onAddExpense = { transactions.add(it) }
                                )
                                BottomScreen.Income -> IncomeScreen(
                                    currency = currency,
                                    categories = incomeCategories,
                                    transactions = transactions.filter { it.type == TransactionType.INCOME },
                                    onAddCategory = { incomeCategories.add(it) },
                                    onEditCategory = { updated ->
                                        incomeCategories.indexOfFirst { it.id == updated.id }.takeIf { it >= 0 }?.let { index ->
                                            incomeCategories[index] = updated
                                        }
                                    },
                                    onDeleteCategory = { cat ->
                                        incomeCategories.remove(cat)
                                        transactions.removeAll { it.category.id == cat.id }
                                    },
                                    onAddIncome = { transactions.add(it) }
                                )
                                BottomScreen.Analytics -> AnalyticsScreen(transactions, expenseCategories, incomeCategories)
                                BottomScreen.Profile -> ProfileScreen(
                                    userName = userName,
                                    currency = currency,
                                    isDarkMode = isDarkMode,
                                    onNameChange = { userName = it },
                                    onCurrencyChange = { currency = it },
                                    onThemeToggle = { isDarkMode = it }
                                )
                            }
                        }
                    }
                }
            )
        }
    }
}

@Composable
private fun BottomNavigationBar(selected: BottomScreen, onSelect: (BottomScreen) -> Unit) {
    NavigationBar(containerColor = MaterialTheme.colorScheme.surface, tonalElevation = 8.dp) {
        BottomScreen.values().forEach { screen ->
            NavigationBarItem(
                icon = {
                    Icon(screen.icon, contentDescription = screen.title)
                },
                label = { Text(screen.title) },
                selected = selected == screen,
                onClick = { onSelect(screen) },
                alwaysShowLabel = false,
                colors = NavDefaults.navigationBarItemColors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant
                )
            )
        }
    }
}

private enum class BottomScreen(val title: String, val icon: androidx.compose.ui.graphics.vector.ImageVector) {
    Home("Home", Icons.Default.Home),
    Expense("Expense", Icons.Default.ReceiptLong),
    Income("Income", Icons.Default.Wallet),
    Analytics("Analytics", Icons.Default.BarChart),
    Profile("Profile", Icons.Default.Person)
}

data class CategoryItem(
    val id: String,
    val title: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector,
    val bgColor: Color,
    val accentColor: Color,
    val type: TransactionType
)

data class MoneyTransaction(
    val id: String,
    val title: String,
    val category: CategoryItem,
    val amount: String,
    val note: String,
    val date: String,
    val type: TransactionType
)

enum class TransactionType { EXPENSE, INCOME }

@Composable
private fun HomeScreen(
    userName: String,
    currency: String,
    transactions: List<MoneyTransaction>,
    expenseCategories: List<CategoryItem>,
    incomeCategories: List<CategoryItem>,
    filterQuery: String,
    onQueryChange: (String) -> Unit,
    onDeleteTransaction: (MoneyTransaction) -> Unit
) {
    val filtered = transactions.filter {
        it.title.contains(filterQuery, true) || it.category.title.contains(filterQuery, true)
    }

    val totalIncome = transactions.filter { it.type == TransactionType.INCOME }.sumOf { it.amount.toIntOrNull() ?: 0 }
    val totalExpense = transactions.filter { it.type == TransactionType.EXPENSE }.sumOf { it.amount.toIntOrNull() ?: 0 }
    val totalBalance = totalIncome - totalExpense

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 16.dp, vertical = 20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            GreetingCard(userName)
        }

        item {
            BalanceCard(totalBalance, totalIncome, totalExpense, currency)
        }

        item {
            MonthlyOverviewCard(expenseCategories, incomeCategories)
        }

        item {
            SearchBar(filterQuery, onQueryChange)
        }

        item {
            Text(
                text = "Recent Transactions",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
        }

        itemsIndexed(filtered) { index, transaction ->
            val dismissState = rememberDismissState(
                confirmStateChange = { value ->
                    if (value == DismissValue.DismissedToStart) {
                        onDeleteTransaction(transaction)
                    }
                    true
                }
            )
            SwipeToDismiss(
                state = dismissState,
                background = {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color(0xFFB00020))
                            .padding(16.dp),
                        contentAlignment = Alignment.CenterEnd
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete",
                            tint = Color.White
                        )
                    }
                },
                directions = setOf(DismissDirection.EndToStart)
            ) {
                TransactionRow(transaction, currency)
            }
            if (index < filtered.lastIndex) {
                item { Spacer(modifier = Modifier.height(8.dp)) }
            }
        }
    }
}

@Composable
private fun GreetingCard(userName: String) {
    Card(
        shape = RoundedCornerShape(24.dp),
        modifier = Modifier.fillMaxWidth(),
        colors = androidx.compose.material3.CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(modifier = Modifier.padding(22.dp)) {
            Text("Good Morning, $userName", style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(6.dp))
            Text("Track your cash flow with smooth design and faster insights.", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}

@Composable
private fun BalanceCard(balance: Int, income: Int, expense: Int, currency: String) {
    Card(
        shape = RoundedCornerShape(28.dp),
        modifier = Modifier.fillMaxWidth(),
        colors = androidx.compose.material3.CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
    ) {
        Column(modifier = Modifier.padding(24.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                Column {
                    Text("Total Balance", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onPrimaryContainer)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("$balance $currency", style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.ExtraBold), color = MaterialTheme.colorScheme.onPrimaryContainer)
                }
                Box(
                    modifier = Modifier
                        .size(52.dp)
                        .background(Color.White.copy(alpha = 0.20f), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.Savings, contentDescription = "Balance", tint = MaterialTheme.colorScheme.primary)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                SummaryChip("Income", "+$income $currency", MaterialTheme.colorScheme.secondaryContainer)
                SummaryChip("Expense", "-$expense $currency", MaterialTheme.colorScheme.errorContainer)
            }
        }
    }
}

@Composable
private fun SummaryChip(title: String, subtitle: String, color: Color) {
    Card(
        shape = RoundedCornerShape(18.dp),
        colors = androidx.compose.material3.CardDefaults.cardColors(containerColor = color),
        modifier = Modifier.weight(1f)
    ) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.Center) {
            Text(title, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            Spacer(modifier = Modifier.height(6.dp))
            Text(subtitle, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
        }
    }
}

@Composable
private fun MonthlyOverviewCard(expenseCategories: List<CategoryItem>, incomeCategories: List<CategoryItem>) {
    Card(shape = RoundedCornerShape(28.dp), modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(22.dp), verticalArrangement = Arrangement.spacedBy(20.dp)) {
            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Column {
                    Text("Monthly Overview", style = MaterialTheme.typography.titleMedium)
                    Text("Spending breakdown and velocity", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
                Icon(Icons.Default.TrendingUp, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
            }
            ChartCard(expenseCategories, incomeCategories)
        }
    }
}

@Composable
private fun ChartCard(expenseCategories: List<CategoryItem>, incomeCategories: List<CategoryItem>) {
    Card(shape = RoundedCornerShape(22.dp), modifier = Modifier.fillMaxWidth(), colors = androidx.compose.material3.CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)) {
        Column(modifier = Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(18.dp)) {
            PieChart(expenseCategories)
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp), modifier = Modifier.fillMaxWidth()) {
                StatChip("Income", "+12.4%", MaterialTheme.colorScheme.secondary)
                StatChip("Expense", "-6.8%", MaterialTheme.colorScheme.error)
            }
        }
    }
}

@Composable
private fun StatChip(text: String, value: String, color: Color) {
    Row(
        modifier = Modifier
            .weight(1f)
            .clip(RoundedCornerShape(18.dp))
            .background(color.copy(alpha = 0.12f))
            .padding(14.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(text, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            Text(value, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
        }
    }
}

@Composable
private fun PieChart(categories: List<CategoryItem>) {
    val total = categories.size.takeIf { it > 0 } ?: 1
    val sweepValues = categories.mapIndexed { index, _ -> (1f + index * 0.6f) }
    val totalSweep = sweepValues.sum()

    Canvas(modifier = Modifier
        .fillMaxWidth()
        .height(160.dp)) {
        var startAngle = -90f
        categories.forEachIndexed { index, category ->
            val sweep = sweepValues[index] / totalSweep * 360f
            drawArc(
                color = category.bgColor,
                startAngle = startAngle,
                sweepAngle = sweep,
                useCenter = true
            )
            startAngle += sweep
        }
    }
}

@Composable
private fun SearchBar(query: String, onQueryChange: (String) -> Unit) {
    OutlinedTextField(
        value = query,
        onValueChange = onQueryChange,
        leadingIcon = { Icon(Icons.Default.Search, null) },
        placeholder = { Text("Search transactions") },
        modifier = Modifier
            .fillMaxWidth()
            .shadow(4.dp, RoundedCornerShape(20.dp)),
        shape = RoundedCornerShape(20.dp)
    )
}

@Composable
private fun TransactionRow(transaction: MoneyTransaction, currency: String) {
    Card(
        shape = RoundedCornerShape(22.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = androidx.compose.material3.CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(modifier = Modifier.padding(18.dp), verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(52.dp)
                    .background(transaction.category.bgColor, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(transaction.category.icon, contentDescription = null, tint = contentColorFor(transaction.category.bgColor))
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(transaction.title, style = MaterialTheme.typography.titleMedium, maxLines = 1, overflow = TextOverflow.Ellipsis)
                Spacer(modifier = Modifier.height(6.dp))
                Text(transaction.note, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
            Column(horizontalAlignment = Alignment.End) {
                Text("${if (transaction.type == TransactionType.EXPENSE) "-" else "+"}${transaction.amount} $currency", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(6.dp))
                Text(transaction.date, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
    }
}

@Composable
private fun ExpenseScreen(
    currency: String,
    categories: List<CategoryItem>,
    transactions: List<MoneyTransaction>,
    onAddCategory: (CategoryItem) -> Unit,
    onEditCategory: (CategoryItem) -> Unit,
    onDeleteCategory: (CategoryItem) -> Unit,
    onAddExpense: (MoneyTransaction) -> Unit
) {
    var showCategoryDialog by remember { mutableStateOf(false) }
    var showExpenseDialog by remember { mutableStateOf(false) }
    var selectedCategory by remember { mutableStateOf<CategoryItem?>(null) }

    LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(18.dp)) {
        item {
            SectionHeader("Expense Categories", "Add categories, track notes and record every purchase")
        }

        itemsIndexed(categories) { _, category ->
            CategoryCard(category, onEdit = { selectedCategory = category; showCategoryDialog = true }, onDelete = { onDeleteCategory(category) }, onAddItem = { selectedCategory = category; showExpenseDialog = true })
        }

        item {
            Button(onClick = { showCategoryDialog = true }, modifier = Modifier.fillMaxWidth()) {
                Icon(Icons.Default.Add, contentDescription = "Add category")
                Spacer(modifier = Modifier.width(10.dp))
                Text("Create new expense category")
            }
        }

        item {
            Text("Recent Expense", style = MaterialTheme.typography.titleMedium)
        }

        itemsIndexed(transactions) { _, transaction ->
            TransactionRow(transaction, currency)
        }
    }

    if (showCategoryDialog) {
        CategoryEditorDialog(
            title = "Add Expense Category",
            initialName = selectedCategory?.title ?: "",
            initialColor = selectedCategory?.bgColor ?: Color(0xFF94DAB2),
            onDismiss = {
                showCategoryDialog = false
                selectedCategory = null
            },
            onSave = { name, color ->
                if (selectedCategory != null) {
                    onEditCategory(selectedCategory!!.copy(title = name, bgColor = color))
                } else {
                    onAddCategory(CategoryItem("exp-${System.currentTimeMillis()}", name, Icons.Default.BusinessCenter, color, color, TransactionType.EXPENSE))
                }
                showCategoryDialog = false
                selectedCategory = null
            }
        )
    }

    if (showExpenseDialog && selectedCategory != null) {
        TransactionEditorDialog(
            title = "Record Expense",
            category = selectedCategory!!,
            currency = currency,
            onDismiss = { showExpenseDialog = false; selectedCategory = null },
            onSave = { amount, note, date ->
                onAddExpense(MoneyTransaction("exp-${System.currentTimeMillis()}", selectedCategory!!.title, selectedCategory!!, amount, note, date, TransactionType.EXPENSE))
                showExpenseDialog = false
                selectedCategory = null
            }
        )
    }
}

@Composable
private fun IncomeScreen(
    currency: String,
    categories: List<CategoryItem>,
    transactions: List<MoneyTransaction>,
    onAddCategory: (CategoryItem) -> Unit,
    onEditCategory: (CategoryItem) -> Unit,
    onDeleteCategory: (CategoryItem) -> Unit,
    onAddIncome: (MoneyTransaction) -> Unit
) {
    var showCategoryDialog by remember { mutableStateOf(false) }
    var showIncomeDialog by remember { mutableStateOf(false) }
    var selectedCategory by remember { mutableStateOf<CategoryItem?>(null) }

    LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(18.dp)) {
        item {
            SectionHeader("Income Streams", "Add salary, bonus or custom income sources")
        }

        itemsIndexed(categories) { _, category ->
            CategoryCard(category, onEdit = { selectedCategory = category; showCategoryDialog = true }, onDelete = { onDeleteCategory(category) }, onAddItem = { selectedCategory = category; showIncomeDialog = true })
        }

        item {
            Button(onClick = { showCategoryDialog = true }, modifier = Modifier.fillMaxWidth()) {
                Icon(Icons.Default.Add, contentDescription = "Add income category")
                Spacer(modifier = Modifier.width(10.dp))
                Text("Create new income category")
            }
        }

        item {
            Text("Income Activity", style = MaterialTheme.typography.titleMedium)
        }

        itemsIndexed(transactions) { _, transaction ->
            TransactionRow(transaction, currency)
        }
    }

    if (showCategoryDialog) {
        CategoryEditorDialog(
            title = "Add Income Category",
            initialName = selectedCategory?.title ?: "",
            initialColor = selectedCategory?.bgColor ?: Color(0xFFB7F8E0),
            onDismiss = {
                showCategoryDialog = false
                selectedCategory = null
            },
            onSave = { name, color ->
                if (selectedCategory != null) {
                    onEditCategory(selectedCategory!!.copy(title = name, bgColor = color))
                } else {
                    onAddCategory(CategoryItem("inc-${System.currentTimeMillis()}", name, Icons.Default.BusinessCenter, color, color, TransactionType.INCOME))
                }
                showCategoryDialog = false
                selectedCategory = null
            }
        )
    }

    if (showIncomeDialog && selectedCategory != null) {
        TransactionEditorDialog(
            title = "Record Income",
            category = selectedCategory!!,
            currency = currency,
            onDismiss = { showIncomeDialog = false; selectedCategory = null },
            onSave = { amount, note, date ->
                onAddIncome(MoneyTransaction("inc-${System.currentTimeMillis()}", selectedCategory!!.title, selectedCategory!!, amount, note, date, TransactionType.INCOME))
                showIncomeDialog = false
                selectedCategory = null
            }
        )
    }
}

@Composable
private fun AnalyticsScreen(transactions: List<MoneyTransaction>, expenseCategories: List<CategoryItem>, incomeCategories: List<CategoryItem>) {
    val expenseCount = transactions.count { it.type == TransactionType.EXPENSE }
    val incomeCount = transactions.count { it.type == TransactionType.INCOME }
    LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(18.dp)) {
        item {
            SectionHeader("Analytics", "Track trends, compare income and spending.")
        }

        item {
            AnalyticsCard("Expense Category Breakdown") {
                ExpensePieChart(expenseCategories)
            }
        }

        item {
            AnalyticsCard("Income vs Expense") {
                ComparisonGraph(expenseCount, incomeCount)
            }
        }

        item {
            AnalyticsCard("Weekly Trends") {
                TrendGraph(listOf(72, 95, 83, 61, 98, 78, 84))
            }
        }
    }
}

@Composable
private fun ProfileScreen(
    userName: String,
    currency: String,
    isDarkMode: Boolean,
    onNameChange: (String) -> Unit,
    onCurrencyChange: (String) -> Unit,
    onThemeToggle: (Boolean) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(18.dp)) {
        Card(shape = RoundedCornerShape(28.dp), modifier = Modifier.fillMaxWidth()) {
            Row(modifier = Modifier.padding(22.dp), verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(76.dp)
                        .background(MaterialTheme.colorScheme.primaryContainer, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text("S", style = MaterialTheme.typography.headlineLarge, color = MaterialTheme.colorScheme.onPrimaryContainer)
                }
                Spacer(modifier = Modifier.width(18.dp))
                Column {
                    Text("Welcome back", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Text(userName, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.ExtraBold)
                }
            }
        }

        Card(shape = RoundedCornerShape(24.dp), modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
                OutlinedTextField(value = userName, onValueChange = onNameChange, label = { Text("Your name") }, modifier = Modifier.fillMaxWidth())
                Text("Preferred currency", style = MaterialTheme.typography.titleSmall)
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    listOf("BDT", "USD", "EUR").forEach { code ->
                        FilterChip(label = code, selected = currency == code, onSelect = { onCurrencyChange(code) })
                    }
                }
                Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                    Column {
                        Text("Theme", style = MaterialTheme.typography.titleSmall)
                        Text(if (isDarkMode) "Night mode" else "Day mode", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                    IconButton(onClick = { onThemeToggle(!isDarkMode) }) {
                        Icon(imageVector = if (isDarkMode) Icons.Outlined.LightMode else Icons.Outlined.DarkMode, contentDescription = null)
                    }
                }
            }
        }

        AnalyticsCard("Personal Finance Settings") {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Text("Notification reminders, export data, currency options and profile sync will be integrated here in future releases.", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                Button(onClick = { /* TODO: export logic */ }, modifier = Modifier.fillMaxWidth()) {
                    Icon(Icons.Default.Save, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Export Data")
                }
            }
        }
    }
}

@Composable
private fun SectionHeader(title: String, subtitle: String) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(title, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
        Text(subtitle, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}

@Composable
private fun CategoryCard(category: CategoryItem, onEdit: () -> Unit, onDelete: () -> Unit, onAddItem: () -> Unit) {
    Card(shape = RoundedCornerShape(26.dp), modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier.padding(18.dp), verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(62.dp)
                    .background(category.bgColor, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(category.icon, contentDescription = null, tint = contentColorFor(category.bgColor))
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(category.title, style = MaterialTheme.typography.titleMedium)
                Text(category.type.name.lowercase().replaceFirstChar { it.uppercase() }, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
            IconButton(onClick = onAddItem) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
            IconButton(onClick = onEdit) {
                Icon(Icons.Default.Edit, contentDescription = "Edit")
            }
            IconButton(onClick = onDelete) {
                Icon(Icons.Default.Delete, contentDescription = "Delete")
            }
        }
    }
}

@Composable
private fun CategoryEditorDialog(
    title: String,
    initialName: String,
    initialColor: Color,
    onDismiss: () -> Unit,
    onSave: (String, Color) -> Unit
) {
    var name by rememberSaveable { mutableStateOf(initialName) }
    var selectedColor by rememberSaveable { mutableStateOf(initialColor) }
    val colors = listOf(Color(0xFF94DAB2), Color(0xFFB5C6F4), Color(0xFFF7D7AC), Color(0xFFB7F8E0), Color(0xFFFAC2E5))

    Dialog(onDismissRequest = onDismiss, properties = DialogProperties(usePlatformDefaultWidth = false)) {
        Card(shape = RoundedCornerShape(28.dp), modifier = Modifier.padding(20.dp)) {
            Column(modifier = Modifier.padding(22.dp), verticalArrangement = Arrangement.spacedBy(18.dp)) {
                Text(title, style = MaterialTheme.typography.titleLarge)
                OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Category name") }, modifier = Modifier.fillMaxWidth())
                Text("Accent color", style = MaterialTheme.typography.bodyMedium)
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    colors.forEach { color ->
                        Box(
                            modifier = Modifier
                                .size(44.dp)
                                .background(color, CircleShape)
                                .border(width = if (selectedColor == color) 3.dp else 0.dp, color = MaterialTheme.colorScheme.primary, shape = CircleShape)
                                .clickable { selectedColor = color }
                        ) {}
                    }
                }
                Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
                    TextButton(onClick = onDismiss) { Text("Cancel") }
                    TextButton(onClick = { if (name.isNotBlank()) onSave(name.trim(), selectedColor) }) { Text("Save") }
                }
            }
        }
    }
}

@Composable
private fun TransactionEditorDialog(
    title: String,
    category: CategoryItem,
    currency: String,
    onDismiss: () -> Unit,
    onSave: (String, String, String) -> Unit
) {
    var amount by rememberSaveable { mutableStateOf("") }
    var note by rememberSaveable { mutableStateOf("") }
    var date by rememberSaveable { mutableStateOf("Today") }

    Dialog(onDismissRequest = onDismiss, properties = DialogProperties(usePlatformDefaultWidth = false)) {
        Card(shape = RoundedCornerShape(28.dp), modifier = Modifier.padding(20.dp)) {
            Column(modifier = Modifier.padding(22.dp), verticalArrangement = Arrangement.spacedBy(18.dp)) {
                Text(title, style = MaterialTheme.typography.titleLarge)
                OutlinedTextField(value = amount, onValueChange = { amount = it }, label = { Text("Amount ($currency)") }, modifier = Modifier.fillMaxWidth())
                OutlinedTextField(value = note, onValueChange = { note = it }, label = { Text("Note") }, modifier = Modifier.fillMaxWidth())
                OutlinedTextField(value = date, onValueChange = { date = it }, label = { Text("Date") }, modifier = Modifier.fillMaxWidth())
                Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
                    TextButton(onClick = onDismiss) { Text("Cancel") }
                    TextButton(onClick = { if (amount.isNotBlank()) onSave(amount.trim(), note.trim(), date.trim()) }) { Text("Save") }
                }
            }
        }
    }
}

@Composable
private fun FilterChip(label: String, selected: Boolean, onSelect: () -> Unit) {
    Card(
        shape = RoundedCornerShape(18.dp),
        modifier = Modifier.clickable { onSelect() },
        colors = androidx.compose.material3.CardDefaults.cardColors(containerColor = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface)
    ) {
        Text(label, modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp), color = if (selected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface)
    }
}

@Composable
private fun AnalyticsCard(title: String, content: @Composable () -> Unit) {
    Card(shape = RoundedCornerShape(26.dp), modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
            Text(title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
            content()
        }
    }
}

@Composable
private fun ExpensePieChart(categories: List<CategoryItem>) {
    Canvas(modifier = Modifier
        .fillMaxWidth()
        .height(180.dp)) {
        val radius = size.minDimension / 2
        var startAngle = -90f
        categories.forEach { category ->
            val sweepAngle = 360f / categories.size
            drawArc(category.bgColor, startAngle, sweepAngle, useCenter = true)
            startAngle += sweepAngle
        }
    }
    Row(horizontalArrangement = Arrangement.spacedBy(12.dp), modifier = Modifier.fillMaxWidth()) {
        categories.take(3).forEach { category ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(modifier = Modifier.size(10.dp).background(category.bgColor, CircleShape))
                Spacer(modifier = Modifier.width(6.dp))
                Text(category.title, style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}

@Composable
private fun ComparisonGraph(expenseCount: Int, incomeCount: Int) {
    Canvas(modifier = Modifier.fillMaxWidth().height(170.dp)) {
        val widthPerPoint = size.width / 6
        val baseY = size.height * 0.8f
        val expenseHeight = baseY - expenseCount.coerceIn(20, 100) * 1.2f
        val incomeHeight = baseY - incomeCount.coerceIn(20, 100) * 1.2f

        drawLine(color = MaterialTheme.colorScheme.error, start = Offset(0f, baseY), end = Offset(size.width, baseY), strokeWidth = 3f)
        drawLine(color = MaterialTheme.colorScheme.error, start = Offset(widthPerPoint, expenseHeight), end = Offset(widthPerPoint * 3, expenseHeight), strokeWidth = 8f, cap = StrokeCap.Round)
        drawLine(color = MaterialTheme.colorScheme.secondary, start = Offset(widthPerPoint * 3, incomeHeight), end = Offset(widthPerPoint * 5, incomeHeight), strokeWidth = 8f, cap = StrokeCap.Round)
    }
    Spacer(modifier = Modifier.height(12.dp))
    Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
        LegendDot(MaterialTheme.colorScheme.secondary, "Income")
        LegendDot(MaterialTheme.colorScheme.error, "Expense")
    }
}

@Composable
private fun TrendGraph(values: List<Int>) {
    Canvas(modifier = Modifier.fillMaxWidth().height(180.dp)) {
        val max = values.maxOrNull()?.toFloat()?.coerceAtLeast(100f) ?: 100f
        val stepX = size.width / (values.size - 1)
        val path = Path().apply {
            values.forEachIndexed { index, value ->
                val x = index * stepX
                val y = size.height - (value / max * size.height)
                if (index == 0) moveTo(x, y) else lineTo(x, y)
            }
        }
        drawPath(path, color = MaterialTheme.colorScheme.primary, style = Stroke(width = 6f, cap = StrokeCap.Round))
    }
}

@Composable
private fun LegendDot(color: Color, label: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(modifier = Modifier.size(12.dp).background(color, CircleShape))
        Spacer(modifier = Modifier.width(6.dp))
        Text(label, style = MaterialTheme.typography.bodySmall)
    }
}
