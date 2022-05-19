package com.zeynab.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewTreeObserver
import android.widget.Toast
import com.zeynab.calculator.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Error
import java.lang.Exception
/*
 Zeynab Babaei
 Calculator Project
 */
class MainActivity : AppCompatActivity() {

    // define one character
    private val myChar: Char = '+'
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onNumbersClicked()
        onOperatorsClicked()
    }

    private fun onOperatorsClicked() {


        binding.btnStar.setOnClickListener {
            // check for last char in the Expression
            try {
                val myText = binding.txtExpression.text.last()
                if (myText != '+' && myText != '/' && myText != '-' && myText != '*') {
                    appendText("*")
                }
            } catch (e: Exception) {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
            }


        }

        binding.btnMines.setOnClickListener {
            try {
                val myText = binding.txtExpression.text.last()
                if (myText != '+' && myText != '/' && myText != '-' && myText != '*') {
                    appendText("-")
                }
            } catch (e: Exception) {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
            }

        }
        binding.btnPlus.setOnClickListener {
            try {
                val myText = binding.txtExpression.text.last()
                if (myText != '+' && myText != '/' && myText != '-' && myText != '*') {
                    appendText("+")
                }
            } catch (e: Exception) {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btndivid.setOnClickListener {

            try {
                val myText = binding.txtExpression.text.last()
                if (myText != '+' && myText != '/' && myText != '-' && myText != '*') {
                    appendText("/")
                }
            } catch (e: Exception) {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
            }


        }

        binding.btnAC.setOnClickListener {


            // AC will delete all the result and expression
            binding.txtExpression.text = ""
            binding.txtResult.text = ""
        }
        binding.btnClear.setOnClickListener {
            // first get the text inside Expression
            var oldText = binding.txtExpression.text.toString()

            // check if expression is not empty
            if (oldText.isNotEmpty()) {
                /// using substring to
                binding.txtExpression.text = oldText.substring(0, oldText.length - 1)
            }

        }

        binding.btnDot.setOnClickListener {

            // check not to have dot (.) at the back of eah other


            var text = binding.txtExpression.text
            // if txtExpression is empty append 0.
            var result = binding.txtResult.text
            if (text.isEmpty() || result.isNotEmpty()) {
                appendText("0.")
            } else if (!text.contains(".") ||
                (text.contains('+')
                        || text.contains('-')
                        || text.contains('/')
                        || text.contains('*'))
                && text.last() != '.'
            ) {

                appendText(".")

            }

        }


        binding.btnEquals.setOnClickListener {

            try {
                // we will use our library
                val expression = ExpressionBuilder(binding.txtExpression.text.toString()).build()
                // create a result variable to do calculation
                val result = expression.evaluate()  // this expression is always double
                val resultLong = result.toLong()   // we cast double to Long

                // here compare if result which is double 135 is == 135.0 we take only 135 not 135.0
                if (result == resultLong.toDouble()) {
                    binding.txtResult.text = resultLong.toString()
                } else {
                    // this is double
                    binding.txtResult.text = result.toString()
                }
            } catch (e: Exception) {
                binding.txtExpression.text = ""
                binding.txtResult.text = ""

                Toast.makeText(this, "Error ", Toast.LENGTH_SHORT).show()
            }


        }

        binding.btncloseParantesis.setOnClickListener {
            //we call append function to show users what is going on operations
            appendText(")")

        }
        binding.btnopenParantesis.setOnClickListener {
            //we call append function to show users what is going on operations
            appendText("(")

        }

    }

    private fun onNumbersClicked() {

        binding.btn0.setOnClickListener {
            //we call append function to show users what is going on operations
            // check if txtExpression is empty do not add any number because is useless
            val text = binding.txtExpression.text
            if (text.isNotEmpty()) {
                appendText("0")
            }

        }

        binding.btn1.setOnClickListener {
            //we call append function to show users what is going on operations
            appendText("1")

        }
        binding.btn2.setOnClickListener {
            //we call append function to show users what is going on operations
            appendText("2")

        }

        binding.btn3.setOnClickListener {
            //we call append function to show users what is going on operations
            appendText("3")

        }
        binding.btn4.setOnClickListener {
            //we call append function to show users what is going on operations
            appendText("4")

        }
        binding.btn5.setOnClickListener {
            //we call append function to show users what is going on operations
            appendText("5")

        }
        binding.btn6.setOnClickListener {
            //we call append function to show users what is going on operations
            appendText("6")

        }
        binding.btn7.setOnClickListener {
            //we call append function to show users what is going on operations
            appendText("7")

        }
        binding.btn8.setOnClickListener {
            //we call append function to show users what is going on operations
            appendText("8")

        }
        binding.btn9.setOnClickListener {
            //we call append function to show users what is going on operations
            appendText("9")

        }
        binding.btnDot.setOnClickListener {
            //we call append function to show users what is going on operations
            appendText(".")

        }


    }


    //this is text view which get string and add at the end of textView (Expression )
    private fun appendText(text: String) {
        if (binding.txtResult.text.isNotEmpty()) {
            binding.txtExpression.text = ""
            binding.txtResult.text = ""
        }

        binding.txtExpression.append(text)

        /// how to see next numbers or charcter in horizental scrollview
        val viewTree: ViewTreeObserver = binding.horizontalViewTxtExpression.viewTreeObserver
        viewTree.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                binding.horizontalViewTxtExpression.viewTreeObserver.removeOnGlobalLayoutListener(
                    this
                )
                binding.horizontalViewTxtExpression.scrollTo(binding.txtExpression.width, 0)
            }
        })


    }


}