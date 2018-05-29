package com.ymd.learn;

import org.activiti.engine.*;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

public class Main {
    public static void main(String[] args) {

        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = engine.getRepositoryService();
        RuntimeService runtimeService = engine.getRuntimeService();
        TaskService taskService = engine.getTaskService();

        repositoryService.createDeployment().addClasspathResource("vacation.bpmn")
                                .deploy();

        ProcessInstance instance = runtimeService.startProcessInstanceByKey("myProcess_1");

        Task task = taskService.createTaskQuery().processInstanceId(instance.getId()).singleResult();
        System.out.println("Task Name = " + task.getName());
        taskService.complete(task.getId());

        task = taskService.createTaskQuery().processInstanceId(instance.getId()).singleResult();
        System.out.println("Task Name = " + task.getName());
        taskService.complete(task.getId());

        taskService.createTaskQuery().processInstanceId(instance.getId()).singleResult();
        System.out.println("Task Name = " + task.getName());

        engine.close();
        System.exit(0);

    }
}
