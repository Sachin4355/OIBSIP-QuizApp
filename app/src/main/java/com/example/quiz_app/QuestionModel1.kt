package com.example.quiz_app

class QuestionModel1{
    var question:String?=null
    var Option1:String?=null
    var Option2:String?=null
    var Option3:String?=null
    var Option4:String?=null
    var ans:String?=null
    constructor()
    constructor(
        question: String?,
        Option1: String?,
        Option2: String?,
        Option3: String?,
        Option4: String?,
        ans: String?
    ) {
        this.question = question
        this.Option1 = Option1
        this.Option2 = Option2
        this.Option3 = Option3
        this.Option4 = Option4
        this.ans = ans
    }

}