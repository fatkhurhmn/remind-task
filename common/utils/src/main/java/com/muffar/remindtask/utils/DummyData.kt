package com.muffar.remindtask.utils

import com.muffar.remindtask.domain.model.Note
import com.muffar.remindtask.domain.model.PriorityType
import com.muffar.remindtask.domain.model.StatusType
import com.muffar.remindtask.domain.model.Task
import java.util.UUID
import kotlin.random.Random

object DummyData {
    fun generateTasks(): List<Task> {
        val tasks = mutableListOf<Task>()
        val titles = listOf(
            "Working on Final Project",
            "Monthly Grocery Shopping",
            "Meeting with Client",
            "Handling Documents",
            "Exercising",
            "Reading Books",
            "Watching Movies",
            "Attending Workshop",
            "Gardening",
            "Listening to Music"
        )

        val descriptions = listOf(
            "Completing implementation of main features of the application",
            "Buying monthly groceries and household items",
            "Meeting with client for project presentation",
            "Managing administrative documents",
            "Doing routine physical exercises",
            "Reading a book about history",
            "Watching the latest comedy movie",
            "Attending programming workshop",
            "Taking care of plants in the garden",
            "Listening to classical music"
        )

        for (i in titles.indices) {
            val task = Task(
                title = titles[i],
                description = descriptions[i],
                deadline = System.currentTimeMillis() + Random.nextLong(86400000 * 7),
                priority = PriorityType.entries.toTypedArray().random(),
                status = StatusType.entries.toTypedArray().random()
            )
            tasks.add(task)
        }

        return tasks
    }

    fun generateNotes(): List<Note> {
        val titles = listOf(
            "Monthly Meeting Agenda",
            "Notes on New Project Presentation",
            "Product Development Ideas",
            "Meeting with Client XYZ",
            "Team Discussion Summary",
            "Workshop Event Planning",
            "Employee Performance Review",
            "Project Schedule Update",
            "Latest Market Research Findings",
            "Time Management Training Notes"
        )

        val descriptions = listOf(
            "Plan for upcoming monthly meeting, including agenda items and confirmed attendance list. Discussion will focus on reviewing last month's performance, project updates, and follow-up plans.",
            "Detailed notes on the presentation of the new project to stakeholders. This includes a brief overview of project background, objectives, scope, and implementation plan. Also includes questions and feedback received during the presentation.",
            "Creative ideas for developing new features for our product. These ideas stem from the latest market analysis and customer feedback. Some highlighted ideas may need further testing before implementation.",
            "Summary of the meeting outcomes with client XYZ, including requests for specification changes and rescheduling. Also discussed are proposed solutions to address challenges faced and next steps in the project.",
            "Summary of team discussions in the recent meeting. This includes discussions on project progress, encountered obstacles, and actions taken to address these issues. Also includes key decisions made in the meeting.",
            "Detailed planning for an upcoming workshop event, including event agenda, speaker list, and presentation materials. The goal of this event is to enhance team skills and knowledge in a specific field.",
            "Evaluation of employee performance in this month's appraisal cycle. This includes individual performance reviews, identification of strengths and weaknesses, and discussions on career development plans for each employee.",
            "Project schedule update based on recent developments, including estimated additional time required to complete specific tasks. This allows management to take proactive actions in managing project risks.",
            "Latest findings and analysis from market research conducted by our research team. This includes key findings, market trends, and potential implications for our marketing and product development strategies.",
            "Important notes from time management training attended yesterday. This includes learned productivity techniques, time management strategies, and practical steps to improve daily work efficiency."
        )

        val notes = mutableListOf<Note>()

        for (i in titles.indices) {
            notes.add(
                Note(
                    UUID.randomUUID(),
                    titles[i],
                    descriptions[i],
                    System.currentTimeMillis()
                )
            )
        }

        return notes
    }
}
