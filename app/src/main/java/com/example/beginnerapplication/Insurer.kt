data class Insurer(
    val id: String,
    val name: String,
    val yearEstablished: Int,
    val headOffice: String,
    val category: String,
    val description: String,
    val contactNumber: String,
    val images: List<String> // Added list of image URLs or resource paths
)

fun getInsurers(): List<Insurer> {
    return listOf(
        Insurer(
            id = "in_01",
            name = "Discovery",
            yearEstablished = 1992,
            headOffice = "Sandton, GP",
            category = "Medical & Life",
            description = "South Africa's largest medical scheme administrator, known for the Vitality rewards program.",
            contactNumber = "0860 99 88 77",
            images = listOf(
                "https://example.com/discovery_logo.png",
                "https://example.com/discovery_building.jpg"
            )
        ),
        Insurer(
            id = "in_02",
            name = "Old Mutual",
            yearEstablished = 1845,
            headOffice = "Pinelands, CPT",
            category = "Investment & Life",
            description = "A pan-African investment, savings, insurance, and banking group with over 175 years of heritage.",
            contactNumber = "0860 50 60 70",
            images = listOf(
                "https://example.com/old_mutual_logo.png",
                "https://example.com/old_mutual_heritage.jpg"
            )
        ),
        Insurer(
            id = "in_03",
            name = "Outsurance",
            yearEstablished = 1998,
            headOffice = "Centurion, GP",
            category = "Short-term & Car",
            description = "Famous for the 'You always get something out' cash out-bonus and straightforward car insurance.",
            contactNumber = "08 600 60 000",
            images = listOf(
                "https://example.com/outsurance_green.png",
                "https://example.com/pointsmen.jpg"
            )
        ),
        Insurer(
            id = "in_04",
            name = "Sanlam",
            yearEstablished = 1918,
            headOffice = "Bellville, CPT",
            category = "Full Suite",
            description = "One of the largest financial services groups in South Africa, focusing on wealth management and life insurance.",
            contactNumber = "021 947 9111",
            images = listOf(
                "https://example.com/sanlam_blue.png",
                "https://example.com/sanlam_office.jpg"
            )
        ),
        Insurer(
            id = "in_05",
            name = "Momentum",
            yearEstablished = 1966,
            headOffice = "Centurion, GP",
            category = "Medical & Life",
            description = "Part of the Momentum Metropolitan Holdings, offering health and financial wellness solutions.",
            contactNumber = "0860 11 78 59",
            images = listOf(
                "https://example.com/momentum_logo.png",
                "https://example.com/multiply_rewards.jpg"
            )
        ),
        Insurer(
            id = "in_06",
            name = "Pineapple",
            yearEstablished = 2017,
            headOffice = "Johannesburg, GP",
            category = "Digital / AI Car",
            description = "A modern, tech-focused insurer that offers decentralized, peer-to-peer insurance via a mobile app.",
            contactNumber = "011 568 2353",
            images = listOf(
                "https://example.com/pineapple_app.png",
                "https://example.com/pineapple_ui.jpg"
            )
        ),
        Insurer(
            id = "in_07",
            name = "First for Women",
            yearEstablished = 2004,
            headOffice = "Johannesburg, GP",
            category = "Short-term / Niche",
            description = "Insurance specifically tailored for women, including unique benefits like Guardian Angel road assistance.",
            contactNumber = "0861 33 93 39",
            images = listOf(
                "https://example.com/ffw_pink.png",
                "https://example.com/guardian_angel.jpg"
            )
        ),
        Insurer(
            id = "in_08",
            name = "AA",
            yearEstablished = 1930,
            headOffice = "Midrand, GP",
            category = "Roadside & Travel",
            description = "The Automobile Association of SA, providing roadside rescue, security, and travel insurance.",
            contactNumber = "0861 000 234",
            images = listOf(
                "https://example.com/aa_yellow.png",
                "https://example.com/aa_van.jpg"
            )
        )
    )
}