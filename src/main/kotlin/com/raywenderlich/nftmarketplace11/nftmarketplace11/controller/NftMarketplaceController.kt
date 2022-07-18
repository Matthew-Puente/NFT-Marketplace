package com.raywenderlich.nftmarketplace11.nftmarketplace11.controller

import com.raywenderlich.nftmarketplace11.nftmarketplace11.exception.NFTNotFoundException
import com.raywenderlich.nftmarketplace11.nftmarketplace11.model.NFT
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController // 1
@RequestMapping("/nfts")
class NftmarketplaceController {

    @GetMapping("/homepage")
    fun getHomePage() = "$name: NFTs Marketplace"


    private var NFTs = mutableListOf(
        NFT(1, "CryptoPunks", 100.0),
        NFT(2, "Sneaky Vampire Syndicate", 36.9),
        NFT(3, "The Sevens (Official)", 0.6),
        NFT(4, "Art Blocks Curated", 1.1),
        NFT(5, "Pudgy Penguins", 2.5),
    )
    @GetMapping("")
    fun getNFTs() = NFTs
    @PostMapping("") // 1
    @ResponseStatus(HttpStatus.CREATED) // 2
    fun postNFT(@RequestBody nft: NFT): NFT { // 3
        val maxId = NFTs.map { it.id }.maxOrNull() ?: 0 // 4
        val nextId = maxId + 1 // 5
        val newNft = NFT(id = nextId, name = nft.name, floor_price = nft.floor_price) // 6
        NFTs.add(newNft) // 7
        return newNft
    }
    @GetMapping("/{id}")
    fun getNFTById(@PathVariable id: Int): NFT {
        val nft = NFTs.firstOrNull { it.id == id }
        return nft ?: throw NFTNotFoundException()
    }

    @PutMapping("/{id}")
    fun changeNFTByID(@PathVariable id:Int, @RequestBody nftDefinition:NFT): NFT {
        var nft: NFT? = NFTs.firstOrNull{it.id == id}
        val newNft = NFT(id = nftDefinition.id, name = nftDefinition.name, floor_price = nftDefinition.floor_price)
        if (nft != null) {
            nft = newNft
        }
        return nft ?: throw NFTNotFoundException()
    }
    @DeleteMapping
    fun deleteNFT(@PathVariable id: Int): NFT{
        var nft = NFTs.firstOrNull{it.id == id}
        val newNft = NFT(id = -1, name = "", floor_price = 0.0)
        if (nft != null) {
            nft = newNft
        }
        return nft ?: throw NFTNotFoundException()
    }

    @Value("\${company_name}")
    private lateinit var name: String



}
