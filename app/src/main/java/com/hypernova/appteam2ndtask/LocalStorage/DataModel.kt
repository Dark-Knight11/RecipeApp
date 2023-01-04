package com.hypernova.appteam2ndtask.LocalStorage
import java.io.Serializable

class DataModel : Serializable {
    var name: String? = null
        private set
    var id: Int? = null
        private set
    var image: String? = null
        private set

    constructor() { }

    constructor(name: String?, id: Int?, image: String?) {

        this.name = name
        this.id = id
        this.image = image
    }
}
