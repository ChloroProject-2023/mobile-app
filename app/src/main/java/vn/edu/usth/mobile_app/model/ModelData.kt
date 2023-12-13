package vn.edu.usth.mobile_app.model

import java.util.Date

class ModelData (
    private var name: String,
    private var description: String,
    private var modelPath: String,
    private var type: String,
    private var creatorName: String,
    private var createdAt: Date,
    private var usage: Int
) {
    fun getName(): String {
        return name
    }
    fun setName(name: String) {
        this.name = name
    }

    fun getDescription(): String {
        return description
    }
    fun setDescription(description: String) {
        this.description = description
    }

    fun getModelPath(): String {
        return modelPath
    }
    fun setModelPath(modelPath: String) {
        this.modelPath = modelPath
    }

    fun getType(): String {
        return type
    }
    fun setType(type: String) {
        this.type = type
    }

    fun getCreatorName(): String {
        return creatorName
    }
    fun setCreatorName(creatorName: String) {
        this.creatorName = creatorName
    }

    fun getCreatedAt(): Date {
        return createdAt
    }
    fun setCreatedAt(createdAt: Long) {
        this.createdAt = Date(createdAt)
    }

    fun getUsage(): Int {
        return usage
    }
    fun setUsage(usage: Int) {
        this.usage = usage
    }
}