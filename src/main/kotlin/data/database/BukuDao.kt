package data.database

import data.model.Buku

object BukuDao {
    var listBuku : MutableList<Buku> = mutableListOf()

    fun insert(buku: Buku) : String {
        return "INSERT INTO buku (idbuku, namabuku, penerbit) VALUES (${buku.idbuku}, \"${buku.namabuku}\", \"${buku.penerbit}\") "
    }

    fun getAll() : String = "SELECT * FROM buku"

    fun getById(id :String) : String = "SELECT * FROM buku WHERE idbuku=$id"
}