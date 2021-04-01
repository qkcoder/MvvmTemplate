package other.mvvmTemplate

import com.android.tools.idea.wizard.template.*
import other.mvvm.activity.mvvmActivityRecipe

val mvvmActivityTemplate
    get() = template {
        revision = 2
        name = "MVVM Activity"
        description = "适用于MVVM框架的Activity"
        minApi = 19
        minBuildApi = 19
        category = Category.Other // Check other categories
        formFactor = FormFactor.Mobile
        screens = listOf(
                WizardUiContext.FragmentGallery,
                WizardUiContext.MenuEntry,
                WizardUiContext.NewProject,
                WizardUiContext.NewModule
        )

        lateinit var layoutName: StringParameter

        val activityClass = stringParameter {
            name = "Activity Name"
            default = "Main"
            help = "只需要输入名字，不要包含Activity"
            constraints = listOf(Constraint.NONEMPTY)
        }

         layoutName = stringParameter {
            name = "Layout Name"
            default = "activity_main"
            help = "请输入布局名字"
            constraints = listOf(Constraint.LAYOUT, Constraint.UNIQUE, Constraint.NONEMPTY)
            suggest = { "${activityToLayout(activityClass.value.toLowerCase())}s" }
        }

        val packageName = defaultPackageNameParameter

        widgets(
                TextFieldWidget(activityClass),
                TextFieldWidget(layoutName),
                PackageNameWidget(packageName)
        )

        recipe = { data: TemplateData ->
            mvvmActivityRecipe(
                    data as ModuleTemplateData,
                    activityClass.value,
                    activityClass.value,
                    layoutName.value
            )
        }
    }

val defaultPackageNameParameter
    get() = stringParameter {
        name = "Package name"
        visible = { !isNewModule }
        default = "com.mycompany.myapp"
        constraints = listOf(Constraint.PACKAGE)
        suggest = { packageName }
    }