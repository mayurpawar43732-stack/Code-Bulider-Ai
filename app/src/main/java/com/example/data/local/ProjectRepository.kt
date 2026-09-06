package com.example.data.local

import kotlinx.coroutines.flow.Flow

class ProjectRepository(private val projectDao: ProjectDao) {
    val allProjects: Flow<List<ProjectEntity>> = projectDao.getAllProjects()

    suspend fun getProjectById(id: Long): ProjectEntity? = projectDao.getProjectById(id)

    suspend fun saveProject(project: ProjectEntity): Long = projectDao.insertProject(project)

    suspend fun updateProject(project: ProjectEntity) = projectDao.updateProject(project)

    suspend fun deleteProject(id: Long) = projectDao.deleteProjectById(id)

    fun getChatMessages(projectId: Long): Flow<List<ChatMessageEntity>> =
        projectDao.getChatMessages(projectId)

    suspend fun addChatMessage(message: ChatMessageEntity) = projectDao.insertChatMessage(message)
}
