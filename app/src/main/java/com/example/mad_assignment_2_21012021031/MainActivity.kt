package com.example.mad_assignment_2_21012021031

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.util.Locale
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    lateinit var questionsList:ArrayList<QuestionModel>
    private var index:Int = 0
    lateinit var questionModel: QuestionModel

    private var correctAnswerCount:Int=0
    private var wrongAnswerCount:Int=0

    lateinit var countDown: TextView
    lateinit var questions: TextView
    lateinit var option1: Button
    lateinit var option2: Button
    lateinit var option3: Button
    lateinit var option4: Button




    private var backPressedTime: Long = 0
    private var backToast: Toast? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        countDown=findViewById(R.id.countdown)
        questions=findViewById(R.id.questions)
        option1=findViewById(R.id.option1)
        option2=findViewById(R.id.option2)
        option3=findViewById(R.id.option3)
        option4=findViewById(R.id.option4)




        questionsList= ArrayList()
        questionsList.add(QuestionModel("Which company owns the Android?","Google","Apple","Nokia"," Samsung","Google"))
        questionsList.add(QuestionModel("Which one is not the Programming Language?","java","kotlin","Notepad","Python","Notepad"))
        questionsList.add(QuestionModel("Choose the Correct option related to Android","Android is Web Browser","Android is Operating System","Android is a web Server","None","Android is Operating System"))
        questionsList.add(QuestionModel("Identify the language on which Android is based upon.","python","C++","Java","None","Java"))
        questionsList.add(QuestionModel("The full form of APK is","Android Page Kit","Android Phone Kit","Android Package Kit","Android Photo Kit","Android Package Kit"))
        questionsList.add(QuestionModel("Identify the parent class of activity.","Adapter","Activity","Fragments","None","Fragments"))
        questionsList.add(QuestionModel("Identify the topmost layer of Android architecture","Application","Application Framework","Linux Kernal","System libraries and android runtime","Application Framework"))
        questionsList.add(QuestionModel("Identify the lowest layer of Android architecture.","Application","System libraries and android runtime","Linux Kernal","Application Framework","Linux Kernal"))
        questionsList.add(QuestionModel("Identify among the following which is not a state in the service lifecycle.","Running","Start","Paused","Destroyed","Paused"))
        questionsList.add(QuestionModel("Identify the dialogue class in Android among the following","DatePicker Dialog","AlertDialog","ProgressDialog","All of the above","All of the above"))
        questionsList.add(QuestionModel("Choose the built-in database of Android.","MySQL","SQLite","Oracle","None","SQLite"))
        questionsList.add(QuestionModel("The full form of ANR is.","Application not rendering","Application not responding","Application not reacting","Application not reachable","Application not responding"))
        questionsList.add(QuestionModel("Identify the layout in which Android arranges its children into rows and columns.","FrameLayout","RelativeLayout"," TableLayout","None","TableLayout"))
        questionsList.add(QuestionModel("JSON stands for ___________\"","Javascript Oriented Notation","Javascript Object Native"," Javascript Object Notation","None","Javascript Object Notation"))
        questionsList.add(QuestionModel("On what is Android web browser-based?","Safari","Firefox"," Open-source WebKit","Chrome","Open-source WebKit"))
        questionsList.add(QuestionModel("On which kernel is Android-based on","Windows","Mac","Linux","Redhat","Linux"))
        questionsList.add(QuestionModel("Under which, an open-source license is Android licensed?","OSS","Apache/MIT","Gnus GPL","Sourceforce","Apache/MIT"))
        questionsList.add(QuestionModel("In which of the following is the Android application Framework provided?","Zip file","Jar file","Object file","EXE file","Jar file"))
        questionsList.add(QuestionModel("AVD stands for","Android Virtual Display","Android Virtual display","Android Virtual Device","Active Virtual Device","Android Virtual Device"))
        questionsList.add(QuestionModel("Identify the parent class of activity.","context","object","None","contextThemeWrapper","contextThemeWrapper"))














        //questionsList.shuffle()
        questionModel= questionsList[index]

        setAllQuestions()

        countdown()










    }


    fun countdown(){
        var duration:Long=TimeUnit.SECONDS.toMillis(15)


        object :CountDownTimer(duration, 1000) {
            override fun onTick(millisUntilFinished: Long) {

                var sDuration:String= String.format(Locale.ENGLISH,
                    "%02d:%02d",
                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished)-TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)))

                countDown.text = sDuration

            }
            override fun onFinish() {
                index++
                if (index<questionsList.size){
                    questionModel=questionsList[index]
                    setAllQuestions()
                    resetBackground()
                    enableButton()
                    countdown()

                }
                else{

                    gameResult()

                }


            }



        }.start()



    }


    private fun correctAns(option: Button){
        option.background=getDrawable(R.drawable.right_bg)

        correctAnswerCount++



    }
    private fun wrongAns(option:Button){

        option.background=resources.getDrawable(R.drawable.wrong_bg)

        wrongAnswerCount++


    }

    private fun gameResult(){
        var intent=Intent(this,ResultActivity::class.java)

        intent.putExtra("correct",correctAnswerCount.toString())
        intent.putExtra("total",questionsList.size.toString())

        startActivity(intent)
    }



    private fun setAllQuestions() {
        questions.text=questionModel.question
        option1.text=questionModel.option1
        option2.text=questionModel.option2
        option3.text=questionModel.option3
        option4.text=questionModel.option4
    }
    private fun enableButton(){
        option1.isClickable=true
        option2.isClickable=true
        option3.isClickable=true
        option4.isClickable=true
    }
    private fun disableButton(){
        option1.isClickable=false
        option2.isClickable=false
        option3.isClickable=false
        option4.isClickable=false
    }
    private fun resetBackground(){
        option1.background=resources.getDrawable(R.drawable.option_bg)
        option2.background=resources.getDrawable(R.drawable.option_bg)
        option3.background=resources.getDrawable(R.drawable.option_bg)
        option4.background=resources.getDrawable(R.drawable.option_bg)
    }
    fun option1Clicked(view:View){
        disableButton()
        if(questionModel.option1==questionModel.answer){
            option1.background=resources.getDrawable(R.drawable.right_bg)


            correctAns(option1)

        }
        else{
            wrongAns(option1)
        }
    }

    fun option2Clicked(view:View){
        disableButton()
        if(questionModel.option2==questionModel.answer){
            option2.background=resources.getDrawable(R.drawable.right_bg)


            correctAns(option2)

        }
        else{
            wrongAns(option2)
        }
    }
    fun option3Clicked(view:View){
        disableButton()
        if(questionModel.option3==questionModel.answer){

            option3.background=resources.getDrawable(R.drawable.right_bg)


            correctAns(option3)


        }
        else{
            wrongAns(option3)
        }
    }
    fun option4Clicked(view:View){
        disableButton()
        if(questionModel.option4==questionModel.answer){
            option4.background=resources.getDrawable(R.drawable.right_bg)


            correctAns(option4)

        }
        else{
            wrongAns(option4)
        }
    }

    override fun onBackPressed() {

        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            backToast?.cancel()
            finish()
            super.onBackPressed()
        }

        else {
            backToast = Toast.makeText(baseContext, "DOUBLE PRESS TO QUIT GAME", Toast.LENGTH_SHORT)
            backToast?.show()
        }
        backPressedTime = System.currentTimeMillis()

    }




}