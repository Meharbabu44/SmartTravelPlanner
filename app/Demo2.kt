class MainActivity:
        ComponentActivity(){
            override fun
                    onCreate(savedInstanceState: Bundle?){
                        super.onCreate(savedInstanceState)
                        setContent {
                           UserInputScreen()
                        }
                    }
        }
        @Composable
        fun UserInputScreen(){
            input var userInput by remember {
                mutableStateOf("")
            }
            Column{
                OutlinedTextField(
                    value = userInput,
                    onValueChange = {
                        userInput = it },
                    label = {
                        Text("Enter your text")
                    },
                    modifier = androidx.compose.ui.Modifier.padding(16.dp)

                    )

                Text(text = "You entered: $userInput",)
                    modifier = androidx.compose.ui.Modifier.padding(16.dp))
            }
        }