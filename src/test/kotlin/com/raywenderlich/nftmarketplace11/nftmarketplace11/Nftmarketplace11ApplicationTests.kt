package com.raywenderlich.nftmarketplace11.nftmarketplace11

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

import com.fasterxml.jackson.databind.ObjectMapper
import com.raywenderlich.nftmarketplace11.nftmarketplace11.model.NFT
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.TestMethodOrder
import org.mockito.internal.matchers.GreaterThan
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.*

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class NftMarketplaceApplicationTests(
	@Autowired val mockMvc: MockMvc,
	@Autowired val objectMapper: ObjectMapper
) {
	@Test
		@Order(1)
	fun `Assert NFTs has CryptoPunks as the first item`() {
		mockMvc.get("/nfts") // 1
			.andExpect { // 2
				status { isOk() } // 3
				content { contentType(MediaType.APPLICATION_JSON) }
				jsonPath("$[0].id") { value(1) } // 4
				jsonPath("$[0].name") { value("CryptoPunks") }
				jsonPath("$[0].floor_price") { value(100) }
				jsonPath("$.length()") { GreaterThan(1) }
			}
	}

	@Test
		@Order(2)
	fun `Assert that we can create an NFT`() {
		mockMvc.get("/nfts/6")
			.andExpect {
				status { isNotFound() }
			}
		val newNFT = NFT(0, "Loot", 45.3)
		mockMvc.post("/nfts") {
			contentType = MediaType.APPLICATION_JSON
			content = objectMapper.writeValueAsString(newNFT)
		}
			.andExpect {
				status { isCreated() }
				content { contentType(MediaType.APPLICATION_JSON) }
				jsonPath("$.name") { value("Loot") }
				jsonPath("$.floor_price") { value(45.3) }
				jsonPath("$.id") { value(6) }
			}
		mockMvc.get("/nfts/6")
			.andExpect {
				status { isOk() }
				content { contentType(MediaType.APPLICATION_JSON) }
				jsonPath("$.name") { value("Loot") }
				jsonPath("$.floor_price") { value(45.3) }
				jsonPath("$.id") { value(6) }
			}
	}

	@Test
		@Order(3)
	fun `Assert that we can change an NFT`(){
		val oldNftOne = NFT(5, "Loot", 45.3)
		mockMvc.put("/nfts/5"){
			contentType = MediaType.APPLICATION_JSON
			content = objectMapper.writeValueAsString(oldNftOne)}
			.andExpect {
				status { isOk() }
				jsonPath("$.name") { value("Loot") }
				jsonPath("$.floor_price") { value(45.3) }
				jsonPath("$.id") { value(5) }
			}

	}
//
	@Test
		@Order(4)
	fun `Assert that we can delete an NFT`(){
		mockMvc.delete("/nfts/5").andExpect{
			status { isOk() }
			jsonPath("$.name") { value("Pudgy Penguins") }
			jsonPath("$.floor_price") { value(2.5) }
			jsonPath("$.id") { value(5) }
		}
		mockMvc.get("/nfts/5")
			.andExpect {
				status { isNotFound() }
			}

	}


}
