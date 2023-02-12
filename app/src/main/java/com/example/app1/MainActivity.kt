package com.example.app1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    private lateinit var question: TextView
    private lateinit var points: TextView

    private lateinit var btn1: Button
    private lateinit var btn2: Button
    private lateinit var btn3: Button
    private lateinit var btn4: Button

    private val rounds = mutableListOf<Round>()
    private var currentRound = 0;

    private fun fillRounds() {
        rounds.run {
            add(
                Round(
                    "Что народная мудрость советует отдать врагу?",
                    "Завтрак",
                    "Обед",
                    "Ужин",
                    "Полдник",
                    3,
                    50
                )
            )
            add(
                Round(
                    "Чему в Туле установлен памятник?",
                    "Хлебу",
                    "Кексу",
                    "Бублику",
                    "Прянику",
                    4,
                    20
                )
            )
            add(
                Round(
                    "Что обычно едят горячим?",
                    "Окрошку",
                    "Гаспачо",
                    "Рассольник",
                    "Свекольник",
                    3,
                    150
                )
            )
        }
    }

    private fun updateUI() {
        question.text = rounds[currentRound].question
        points.text = rounds[currentRound].value.toString()
        btn1.text = rounds[currentRound].answer1
        btn2.text = rounds[currentRound].answer2
        btn3.text = rounds[currentRound].answer3
        btn4.text = rounds[currentRound].answer4
    }

    private fun checkAnswer(givenId: Int) = (givenId == rounds[currentRound].rightId)

    private fun goNextRound() : Boolean {
        if (currentRound >= rounds.size - 1) { return false }
        currentRound ++
        updateUI()
        return true
    }

    private fun processRound(givenId: Int) {
        if (checkAnswer(givenId)) {
            if (!goNextRound()) {
                Toast.makeText(this, "ПОБЕДА!", Toast.LENGTH_SHORT).show()
                finish()
            }
        } else {
            Toast.makeText(this, "НЕ ПОБЕДА!", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        question = findViewById(R.id.tvQuestion)
        points = findViewById(R.id.tvValue)

        btn1 = findViewById(R.id.button)
        btn2 = findViewById(R.id.button1)
        btn3 = findViewById(R.id.button2)
        btn4 = findViewById(R.id.button3)
        fillRounds()
        updateUI()
        btn1.setOnClickListener { processRound(1) }
        btn2.setOnClickListener { processRound(2) }
        btn3.setOnClickListener { processRound(3) }
        btn4.setOnClickListener { processRound(4) }
    }
}