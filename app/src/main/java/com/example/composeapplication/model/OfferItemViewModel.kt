package com.example.composeapplication.model

import com.example.composeapplication.R

class OfferItemViewModel(val title: String = "", val description: String = "", val image: Int = 0, val price: Int = 0)

enum class DefaultOffer(val viewModel: OfferItemViewModel) {
    BARCELONA(
        OfferItemViewModel(
            "Barcelona, 3 nights",
            "Dwuahdiuwa dwhahdowadh ahwd awd hoahd iowah ioiwahd oiahd iaowhd oiwahd ioahi awh oidawdoihioawhd haoidh oahd hoiah dioawhio hadwd doa. " +
                    "Ce tare e in Barcelona, abia astept sa merg din nou sa ma plimb pe rambla sa beau o sangria!",
            R.drawable.barcelona,
            300
        )
    ),
    MALDIVE(
        OfferItemViewModel(
            "Maldive, 7 nights",
            "Dwadhawuduioahdw aohdoawhd aiowdhai dwah oiawiod hio awhdh aiod hawd houawhd iawhdihaioa oidahw  adwwajdoiawjodj waiojdio awjoi jdawoidj ioawwdj" +
                    "Si in Maldive as merge, arata foarte bine, mergem la scufundari ne mananca rechinii.",
            R.drawable.maldive,
            1050
        )
    ),
    THAILAND(
        OfferItemViewModel(
            "Thailand, 10 nights",
            "Pfheisfh iugh ofh ftth ofjh ioft ioh fdti hiod hoid oid oijd hjdoihidfhdo c gdoj hoidj hoijdiohdiorg dirogj iodrjg iodj rijdog oerd " +
                    "In Thailanda e fain ca vin soparlele alea mari si ti se pun pe prosop cu tine. Vreau sa prind una si sa o aduc acasa!",
            R.drawable.thailand,
            1250
        )
    )
}