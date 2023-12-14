import javax.swing.*
import java.awt.BorderLayout
import java.awt.Dimension
import java.awt.GridLayout

fun main() {
    val frame = JFrame("Calculator")
    val container = frame.contentPane
    container.layout = BorderLayout()

    val textField = JTextField()
    // Set preferred height of the text field, assuming button height is around 50 pixels
    textField.preferredSize = Dimension(200, 50)
    container.add(textField, BorderLayout.NORTH)

    // Panel for buttons
    val buttonPanel = JPanel()
    buttonPanel.layout = GridLayout(4, 4)  // 4 rows, 4 cols
    val buttons = arrayOf(
        "7", "8", "9", "+",
        "4", "5", "6", "-",
        "1", "2", "3", "*",
        "C", "0", "=", "/"
    )

    buttons.forEach { label ->
        val button = JButton(label)
        buttonPanel.add(button)
        button.addActionListener {
            performAction(label, textField)
        }
    }

    container.add(buttonPanel, BorderLayout.CENTER)

    frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
    frame.setSize(400, 400)
    frame.isVisible = true
}

fun performAction(label: String, textField: JTextField) {
    when(label) {
        "C" -> textField.text = ""
        "=" -> textField.text = calculate(textField.text).toString()
        else -> textField.text = textField.text + label
    }
}

fun calculate(expression: String): Double {
    val parts = expression.split("+", "-", "*", "/")
    if (parts.size != 2) return 0.0

    val a = parts[0].toDouble()
    val b = parts[1].toDouble()

    return when {
        expression.contains("+") -> a + b
        expression.contains("-") -> a - b
        expression.contains("*") -> a * b
        expression.contains("/") -> a / b
        else -> 0.0
    }
}
