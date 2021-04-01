package com.qk.mvvm.template.services

import com.intellij.openapi.project.Project
import com.qk.mvvm.template.MyBundle

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
