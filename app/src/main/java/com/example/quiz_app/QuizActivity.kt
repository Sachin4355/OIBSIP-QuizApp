package com.example.quiz_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.quiz_app.databinding.ActivityQuizBinding
import com.google.firebase.ktx.Firebase
import com.google.firebase.firestore.ktx.firestore

class QuizActivity : AppCompatActivity() {
    private lateinit var binding: ActivityQuizBinding
    private lateinit var questionList: ArrayList<QuestionModel1>
    private var currentQuestionIndex: Int = 0
    private var score: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        questionList = ArrayList()

        Firebase.firestore.collection("quiz")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    val questionModel = document.toObject(QuestionModel1::class.java)
                    questionList.add(questionModel)
                }

                // Display the first question
                displayQuestion(currentQuestionIndex)
            }

        // Set click listeners for options
        binding.Option1.setOnClickListener {
            checkAnswer(binding.Option1.text.toString())
        }
        binding.Option2.setOnClickListener {
            checkAnswer(binding.Option2.text.toString())
        }
        binding.Option3.setOnClickListener {
            checkAnswer(binding.Option3.text.toString())
        }
        binding.Option4.setOnClickListener {
            checkAnswer(binding.Option4.text.toString())
        }
    }

    private fun displayQuestion(index: Int) {
        if (index < questionList.size) {
            val currentQuestion = questionList[index]
            binding.question.text = currentQuestion.question
            binding.Option1.text = currentQuestion.Option1
            binding.Option2.text = currentQuestion.Option2
            binding.Option3.text = currentQuestion.Option3
            binding.Option4.text = currentQuestion.Option4

            resetOptionBackground()
        } else {
            val intent = Intent(this, ScoreActivity::class.java)
            intent.putExtra("SCORE", score)
            startActivity(intent)
            finish()
        }
    }

    private fun checkAnswer(selectedOption: String) {
        val currentQuestion = questionList[currentQuestionIndex]
        if (selectedOption.trim() == currentQuestion.ans?.trim()) {
            score++
        }

        currentQuestionIndex++
        displayQuestion(currentQuestionIndex)
    }

    private fun resetOptionBackground() {
        binding.Option1.setBackgroundResource(R.drawable.default_option_background)
        binding.Option2.setBackgroundResource(R.drawable.default_option_background)
        binding.Option3.setBackgroundResource(R.drawable.default_option_background)
        binding.Option4.setBackgroundResource(R.drawable.default_option_background)
    }
}
