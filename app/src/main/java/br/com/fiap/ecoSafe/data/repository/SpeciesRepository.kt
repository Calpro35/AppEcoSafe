package br.com.fiap.ecoSafe.data.repository

import android.content.Context
import br.com.fiap.ecoSafe.data.model.Specie
import java.io.File

class SpeciesRepository {

    private fun fromCsv(fields: List<String>): Specie {
        return Specie(
            id = fields[0],
            grupoTaxonomico = fields[1],
            ordem = fields[2],
            familia = fields[3],
            especie = fields[4],
            categoria2014 = fields[6].takeIf { it.isNotEmpty() },
            categoria2021 = fields[7].takeIf { it.isNotEmpty() },
            ew = fields[8].trim() == "X",
            cr = fields[9].trim() == "X",
            en = fields[10].trim() == "X",
            vu = fields[11].trim() == "X",
            re = fields[12].trim() == "X",
            ex = fields[13].trim() == "X"
        )
    }

    fun getAllSpecies(csvFilePath: String): List<Specie> {
        val lines = File(csvFilePath).readLines()
        return lines
            .mapNotNull { line ->
                val fields = line.split(";")
                if (fields.size > 13) fromCsv(fields) else null
            }
    }

    fun getSpecieByName(context: Context, specieName: String): Specie? {
        val inputStream = context.assets.open("fauna_ameacada_2021.txt")
        val lines = inputStream.bufferedReader().use { it.readLines() }

        return lines
            .mapNotNull { line ->
                val fields = line.split(";")
                if (fields.size > 13) fromCsv(fields) else null
            }
            .find { it.especie.equals(specieName, ignoreCase = true) }
    }

}