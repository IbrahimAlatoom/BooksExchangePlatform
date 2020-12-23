package com.jordan.booksexchange.models

class Request(val userId :String?, val publisherId :String? , val postId :String?) {
    constructor() : this("","","")
}