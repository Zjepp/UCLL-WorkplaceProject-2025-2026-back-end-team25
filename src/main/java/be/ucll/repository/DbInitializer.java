package be.ucll.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import be.ucll.model.Antwoorden;
import be.ucll.model.Faq;
import be.ucll.model.Resultaat;
import be.ucll.model.User;
import be.ucll.model.Vragen;
import be.ucll.model.User.Role;
import jakarta.annotation.PostConstruct;

@Component
public class DbInitializer {

        private UserRepository userRepository;
        private VragenRepository vragenRepository;
        private AntwoordenRepository antwoordenRepository;
        private ResultaatRepository resultaatRepository;
        private FaqRepository faqRepository;

        @Autowired
        private BCryptPasswordEncoder passwordEncoder;

        @Autowired
        public DbInitializer(UserRepository userRepository, VragenRepository vragenRepository,
                        AntwoordenRepository antwoordenRepository, ResultaatRepository resultaatRepository,
                        FaqRepository faqRepository) {
                this.userRepository = userRepository;
                this.vragenRepository = vragenRepository;
                this.antwoordenRepository = antwoordenRepository;
                this.resultaatRepository = resultaatRepository;
                this.faqRepository = faqRepository;
        }

        @PostConstruct
        public void initialize() {
                // Create the answer and question

                // Save the question first (this cascades Antwoorden)

                // --- 1 ---
                Antwoorden a1 = new Antwoorden(true, "Een metabole ziekte gekenmerkt door hyperglycemie");
                Antwoorden a2 = new Antwoorden(false, "Een infectieziekte van de pancreas");
                Antwoorden a3 = new Antwoorden(false, "Een leveraandoening veroorzaakt door vetopstapeling");
                Antwoorden a4 = new Antwoorden(false, "Een hormonale aandoening van de schildklier");
                Vragen vraag1 = new Vragen("standaard-choice", "Wat is de definitie van diabetes mellitus?",
                                "Hormonaal stelsel", List.of(a1, a2, a3, a4),
                                null);

                // --- 2 ---
                Antwoorden a5 = new Antwoorden(true, "Absoluut insulinetekort (type 1) of relatief tekort (type 2)");
                Antwoorden a6 = new Antwoorden(false, "Te hoge productie van glucagon in de lever");
                Antwoorden a7 = new Antwoorden(false, "Een tekort aan glycogeen in de spieren");
                Antwoorden a8 = new Antwoorden(false, "Een tekort aan vitamine D");
                Vragen vraag2 = new Vragen("standaard-choice", "Wat veroorzaakt diabetes mellitus primair?",
                                "Hormonaal stelsel", List.of(a5, a6, a7, a8),
                                null);

                // --- 3 ---
                Antwoorden a9 = new Antwoorden(true, "Type 2 diabetes");
                Antwoorden a10 = new Antwoorden(false, "Type 1 diabetes");
                Antwoorden a11 = new Antwoorden(false, "Zwangerschapsdiabetes");
                Antwoorden a12 = new Antwoorden(false, "Secundaire diabetes");
                Vragen vraag3 = new Vragen("standaard-choice", "Welk type diabetes komt wereldwijd het meest voor?",
                                "Hormonaal stelsel",
                                List.of(a9, a10, a11, a12), null);

                // --- 4 ---
                Antwoorden a13 = new Antwoorden(true, "537 miljoen");
                Antwoorden a14 = new Antwoorden(false, "300 miljoen");
                Antwoorden a15 = new Antwoorden(false, "700 miljoen");
                Antwoorden a16 = new Antwoorden(false, "1 miljard");
                Vragen vraag4 = new Vragen("standaard-choice",
                                "Hoeveel volwassenen wereldwijd leefden in 2021 met diabetes volgens de IDF?",
                                "Hormonaal stelsel",
                                List.of(a13, a14, a15, a16), null);

                // --- 5 ---
                Antwoorden a17 = new Antwoorden(true, "1 op 10 volwassenen");
                Antwoorden a18 = new Antwoorden(false, "1 op 20 volwassenen");
                Antwoorden a19 = new Antwoorden(false, "1 op 50 volwassenen");
                Antwoorden a20 = new Antwoorden(false, "1 op 100 volwassenen");
                Vragen vraag5 = new Vragen("standaard-choice",
                                "Hoeveel volwassenen wereldwijd hebben diabetes volgens de IDF 2021?",
                                "Hormonaal stelsel",
                                List.of(a17, a18, a19, a20), null);

                // --- 6 ---
                Antwoorden a21 = new Antwoorden(true, "Een verhoogde kans op cardiovasculaire complicaties");
                Antwoorden a22 = new Antwoorden(false, "Een verlaagde kans op nierproblemen");
                Antwoorden a23 = new Antwoorden(false, "Een hogere weerstand tegen infecties");
                Antwoorden a24 = new Antwoorden(false, "Een snellere wondgenezing");
                Vragen vraag6 = new Vragen("standaard-choice",
                                "Wat is een typisch langetermijneffect van chronische hyperglycemie?",
                                "Cardiovasculair",
                                List.of(a21, a22, a23, a24), null);

                // --- 7 ---
                Antwoorden a25 = new Antwoorden(true, "Microvasculaire en macrovasculaire complicaties");
                Antwoorden a26 = new Antwoorden(false, "Enkel acute hypoglycemieën");
                Antwoorden a27 = new Antwoorden(false, "Alleen infecties");
                Antwoorden a28 = new Antwoorden(false, "Enkel leververvetting");
                Vragen vraag7 = new Vragen("standaard-choice",
                                "Welke twee hoofdgroepen van complicaties worden veroorzaakt door diabetes?",
                                "Hormonaal stelsel",
                                List.of(a25, a26, a27, a28), null);

                // --- 8 ---
                Antwoorden a29 = new Antwoorden(true, "Retinopathie, nefropathie, neuropathie");
                Antwoorden a30 = new Antwoorden(false, "Longfibrose, hepatitis, trombose");
                Antwoorden a31 = new Antwoorden(false, "Artritis, astma, psoriasis");
                Antwoorden a32 = new Antwoorden(false, "Migraine, dementie, bloedarmoede");
                Vragen vraag8 = new Vragen("standaard-choice", "Wat zijn voorbeelden van microvasculaire complicaties?",
                                "Hormonaal stelsel",
                                List.of(a29, a30, a31, a32), null);

                // --- 9 ---
                Antwoorden a33 = new Antwoorden(true, "Hart- en vaatziekten");
                Antwoorden a34 = new Antwoorden(false, "Botontkalking");
                Antwoorden a35 = new Antwoorden(false, "Huidziekten");
                Antwoorden a36 = new Antwoorden(false, "Longinfecties");
                Vragen vraag9 = new Vragen("standaard-choice",
                                "Wat is een voorbeeld van een macrovasculaire verwikkeling bij diabetes?",
                                "Cardiovasculair", List.of(a33, a34, a35, a36),
                                null);

                // --- 10 ---
                Antwoorden a37 = new Antwoorden(true, "Vermoeidheid, polyurie, polydipsie, gewichtsverlies");
                Antwoorden a38 = new Antwoorden(false, "Hoofdpijn, misselijkheid, diarree");
                Antwoorden a39 = new Antwoorden(false, "Hoge koorts, huiduitslag, keelpijn");
                Antwoorden a40 = new Antwoorden(false, "Slaperigheid, spraakstoornissen, verlamming");
                Vragen vraag10 = new Vragen("standaard-choice",
                                "Wat zijn typische symptomen van type 1 diabetes bij aanvang?", "Hormonaal stelsel",
                                List.of(a37, a38, a39, a40), null);

                // --- 11 ---
                Antwoorden a41 = new Antwoorden(true, "T-cel gemedieerde destructie van β-cellen");
                Antwoorden a42 = new Antwoorden(false, "Overmatige glucagonproductie");
                Antwoorden a43 = new Antwoorden(false, "Teveel vetopslag in de pancreas");
                Antwoorden a44 = new Antwoorden(false, "Onvoldoende koolhydraatinname");
                Vragen vraag11 = new Vragen("memory-choice", "Wat is de primaire oorzaak van type 1 diabetes mellitus?",
                                "Hormonaal stelsel",
                                List.of(a41, a42, a43, a44), null);

                // --- 12 ---
                Antwoorden a45 = new Antwoorden(true, "De incretinehormonen GLP-1 en GIP verhogen de insulinesecretie");
                Antwoorden a46 = new Antwoorden(false, "Insuline verlaagt de incretineproductie");
                Antwoorden a47 = new Antwoorden(false, "Incretines remmen de insulinesecretie");
                Antwoorden a48 = new Antwoorden(false, "Incretines veroorzaken hypoglycemie");
                Vragen vraag12 = new Vragen("memory-choice", "Wat doen incretinehormonen in de regulatie van glucose?",
                                "Hormonaal stelsel",
                                List.of(a45, a46, a47, a48), null);

                // --- 13 ---
                Antwoorden a49 = new Antwoorden(true,
                                "Het verschil in insulinevrijstelling bij orale versus IV glucose");
                Antwoorden a50 = new Antwoorden(false, "Het effect van insuline op vetafbraak");
                Antwoorden a51 = new Antwoorden(false, "De hoeveelheid glycogeen in de lever");
                Antwoorden a52 = new Antwoorden(false, "De opname van glucose in de spieren");
                Vragen vraag13 = new Vragen("memory-choice", "Wat beschrijft het incretine-effect?",
                                "Hormonaal stelsel", List.of(a49, a50, a51, a52),
                                null);

                // --- 14 ---
                Antwoorden a53 = new Antwoorden(true, "Het incretine-effect is verminderd");
                Antwoorden a54 = new Antwoorden(false, "Het incretine-effect is verhoogd");
                Antwoorden a55 = new Antwoorden(false, "Er is geen verschil");
                Antwoorden a56 = new Antwoorden(false, "Het hangt af van het geslacht");
                Vragen vraag14 = new Vragen("memory-choice",
                                "Hoe is het incretine-effect bij patiënten met type 2 diabetes?", "Hormonaal stelsel",
                                List.of(a53, a54, a55, a56), null);

                // --- 15 ---
                Antwoorden a57 = new Antwoorden(true, "Sitagliptine, Linagliptine, Vildagliptine");
                Antwoorden a58 = new Antwoorden(false, "Metformine, Empagliflozine, Glibenclamide");
                Antwoorden a59 = new Antwoorden(false, "Insuline, Glucagon, Adrenaline");
                Antwoorden a60 = new Antwoorden(false, "Dapagliflozine, Semaglutide, Liraglutide");
                Vragen vraag15 = new Vragen("memory-choice", "Wat zijn voorbeelden van DPP-4 inhibitoren?",
                                "Hormonaal stelsel",
                                List.of(a57, a58, a59, a60), null);

                // --- 16 ---
                Antwoorden a61 = new Antwoorden(true, "Liraglutide, Semaglutide, Dulaglutide");
                Antwoorden a62 = new Antwoorden(false, "Metformine, Glibenclamide, Sitagliptine");
                Antwoorden a63 = new Antwoorden(false, "Dapagliflozine, Canagliflozine, Empagliflozine");
                Antwoorden a64 = new Antwoorden(false, "Insuline lispro, NPH, Glargine");
                Vragen vraag16 = new Vragen("memory-choice", "Wat zijn voorbeelden van GLP-1 receptoragonisten?",
                                "Hormonaal stelsel",
                                List.of(a61, a62, a63, a64), null);

                // --- 17 ---
                Antwoorden a65 = new Antwoorden(true, "Empagliflozine, Dapagliflozine, Canagliflozine");
                Antwoorden a66 = new Antwoorden(false, "Liraglutide, Dulaglutide, Semaglutide");
                Antwoorden a67 = new Antwoorden(false, "Metformine, Pioglitazone, Acarbose");
                Antwoorden a68 = new Antwoorden(false, "Sitagliptine, Linagliptine, Alogliptine");
                Vragen vraag17 = new Vragen("memory-choice", "Wat zijn voorbeelden van SGLT2-inhibitoren?",
                                "Hormonaal stelsel",
                                List.of(a65, a66, a67, a68), null);

                // --- 18 ---
                Antwoorden a69 = new Antwoorden(true, "Een SGLT2-remmer vermindert de glucose-reabsorptie in de nier");
                Antwoorden a70 = new Antwoorden(false, "Een SGLT2-remmer verhoogt de insulineproductie");
                Antwoorden a71 = new Antwoorden(false, "Een SGLT2-remmer verhoogt de glucagonsecretie");
                Antwoorden a72 = new Antwoorden(false, "Een SGLT2-remmer verhoogt de eetlust");
                Vragen vraag18 = new Vragen("memory-choice", "Wat is het werkingsmechanisme van een SGLT2-remmer?",
                                "Hormonaal stelsel",
                                List.of(a69, a70, a71, a72), null);

                // --- 19 ---
                Antwoorden a73 = new Antwoorden(true, "Type 2 diabetes is verantwoordelijk voor >40% van de gevallen");
                Antwoorden a74 = new Antwoorden(false, "Type 1 diabetes veroorzaakt de meeste nierinsufficiënties");
                Antwoorden a75 = new Antwoorden(false, "Hypertensie is de belangrijkste oorzaak");
                Antwoorden a76 = new Antwoorden(false, "Er is geen verband tussen diabetes en nierfalen");
                Vragen vraag19 = new Vragen("memory-choice",
                                "Wat is de belangrijkste oorzaak van chronische nierinsufficiëntie wereldwijd?",
                                "Uro-genitaal stelsel",
                                List.of(a73, a74, a75, a76), null);

                // --- 20 ---
                Antwoorden a77 = new Antwoorden(true, "Minder dan 60 ml/min/1.73m²");
                Antwoorden a78 = new Antwoorden(false, "Meer dan 100 ml/min/1.73m²");
                Antwoorden a79 = new Antwoorden(false, "Tussen 70 en 90 ml/min/1.73m²");
                Antwoorden a80 = new Antwoorden(false, "Tussen 120 en 150 ml/min/1.73m²");
                Vragen vraag20 = new Vragen("memory-choice", "Bij welke eGFR spreken we van nierinsufficiëntie?",
                                "Uro-genitaal stelsel",
                                List.of(a77, a78, a79, a80), null);

                // --- 21 ---
                Antwoorden a81 = new Antwoorden(true, "diarree");
                Vragen vraag21 = new Vragen("hangman",
                                "Wat zijn de meest voorkomende bijwerkingen bij de start van metformine?",
                                "Hormonaal stelsel", List.of(a81),
                                null);

                // --- 22 ---
                Antwoorden a85 = new Antwoorden(true, "Maaltijd");
                Vragen vraag22 = new Vragen("hangman", "Wanneer neem je metformine idealiter in?",
                                "Hormonaal stelsel",
                                List.of(a85), null);

                // --- 23 ---
                Antwoorden a89 = new Antwoorden(true, "insulinesecretie");
                Vragen vraag23 = new Vragen("hangman", "Wat stimuleert sulfonylurea?",
                                "Hormonaal stelsel",
                                List.of(a89), null);

                // --- 24 ---
                Antwoorden a93 = new Antwoorden(true, "Hypoglycemie");
                Vragen vraag24 = new Vragen("hangman", "Wat is de meest typische bijwerking van sulfonylurea?",
                                "Hormonaal stelsel",
                                List.of(a93), null);

                // --- 25 ---
                Antwoorden a97 = new Antwoorden(true, "Nierfalen");
                Vragen vraag25 = new Vragen("hangman", "Welke aandoening vormt een belangrijke contra-indicatie voor sulfonylurea?",
                                "Uro-genitaal stelsel",
                                List.of(a97), null);

                // --- 26 ---
                Antwoorden a101 = new Antwoorden(true, "Veilig");
                Vragen vraag26 = new Vragen("hangman", "Hoe staat het veiligheidsprofiel van DPP-4-inhibitoren bekend?",
                                "Hormonaal stelsel",
                                List.of(a101), null);

                // --- 27 ---
                // Antwoorden a105 = new Antwoorden(true, "Cardiorenaal veilig en gewichtsneutraal");
                // Vragen vraag27 = new Vragen("hangman", "Wat is een voordeel van DPP-4 inhibitoren?",
                //                 "Hormonaal stelsel",
                //                 List.of(a105), null);

                // --- 28 ---
                Antwoorden a109 = new Antwoorden(true, "Gewichtsverlies");
                Vragen vraag28 = new Vragen("hangman",
                                "Welk bijkomend positief effect heeft een GLP-1-receptoragonist?", "Cardiovasculair",
                                List.of(a109), null);

                // // --- 29 ---
                // Antwoorden a113 = new Antwoorden(true, "GLP-1 RA heeft cardiovasculaire voordelen");
                // Vragen vraag29 = new Vragen("hangman", "Wat tonen studies aan over GLP-1 RA en cardiovasculair risico?",
                //                 "Cardiovasculair",
                //                 List.of(a113), null);

                // --- 30 ---
                // Antwoorden a117 = new Antwoorden(true, "Minder dan 5 dagen: alsnog toedienen");
                // Vragen vraag30 = new Vragen("hangman", "Wat doe je bij een gemiste Ozempic® injectie?",
                //                 "Hormonaal stelsel",
                //                 List.of(a117), null);

                // --- 31 ---
                Antwoorden a121 = new Antwoorden(true, "Minstens 30 minuten voor het ontbijt");
                Vragen vraag31 = new Vragen("connect-choice",
                                "Wanneer moet Rybelsus® (orale semaglutide) worden ingenomen?", "Hormonaal stelsel",
                                List.of(a121), null);

                // --- 32 ---
                Antwoorden a125 = new Antwoorden(true, "Met maximaal 120 ml water doorslikken");
                Vragen vraag32 = new Vragen("connect-choice",
                                "Hoe moet Rybelsus® worden ingenomen volgens voorschrift?", "Hormonaal stelsel",
                                List.of(a125), null);

                // --- 33 ---
                Antwoorden a129 = new Antwoorden(true, "Wachten tot 30 minuten na inname om te eten of drinken");
                Vragen vraag33 = new Vragen("connect-choice",
                                "Wat is belangrijk na inname van orale semaglutide (Rybelsus®)?", "Hormonaal stelsel",
                                List.of(a129), null);

                // --- 34 ---
                Antwoorden a133 = new Antwoorden(true, "Misselijkheid en braken");
                Vragen vraag34 = new Vragen("connect-choice",
                                "Wat is de meest voorkomende nevenwerking van GLP-1 receptoragonisten?",
                                "Hormonaal stelsel",
                                List.of(a133), null);

                // --- 35 ---
                Antwoorden a137 = new Antwoorden(true, "Kleine porties en vetarme maaltijden");
                Vragen vraag35 = new Vragen("connect-choice",
                                "Welke voedingsadviezen helpen bij GLP-1-gerelateerde misselijkheid?",
                                "Gezonde levensstijl", List.of(a137),
                                null);

                // --- 36 ---
                Antwoorden a141 = new Antwoorden(true, "≥150 minuten matige activiteit per week");
                Vragen vraag36 = new Vragen("connect-choice",
                                "Wat is de aanbevolen hoeveelheid fysieke activiteit per week bij diabetes?",
                                "Gezonde levensstijl",
                                List.of(a141), null);

                // --- 37 ---
                Antwoorden a145 = new Antwoorden(true, "5-10% gewichtsverlies verlaagt cardiovasculair risico");
                Vragen vraag37 = new Vragen("connect-choice",
                                "Wat is het effect van gewichtsverlies op cardiovasculaire risico's bij diabetes?",
                                "Cardiovasculair",
                                List.of(a145), null);

                // --- 38 ---
                Antwoorden a149 = new Antwoorden(true,
                                "Hart- en vaatziekten zijn oorzaak van 60–80% van de sterfte bij diabetes");
                Vragen vraag38 = new Vragen("connect-choice",
                                "Wat is de belangrijkste doodsoorzaak bij diabetespatiënten?", "Cardiovasculair",
                                List.of(a149), null);

                // --- 39 ---
                Antwoorden a153 = new Antwoorden(true, "SGLT2-remmers verminderen ziekenhuisopnames voor hartfalen");
                Vragen vraag39 = new Vragen("connect-choice", "Wat is het effect van SGLT2-inhibitoren op hartfalen?",
                                "Cardiovasculair",
                                List.of(a153), null);

                // --- 40 ---
                Antwoorden a157 = new Antwoorden(true, "Chronische nierschade");
                Vragen vraag40 = new Vragen("connect-choice",
                                "Welke complicatie treedt vaak op bij diabetes en beïnvloedt eGFR?",
                                "Uro-genitaal stelsel",
                                List.of(a157), null);

                // --- 41 ---
                Antwoorden a161 = new Antwoorden(true, "Reversie mogelijk bij vroegtijdige gewichtsreductie");
                Vragen vraag41 = new Vragen("connect-choice", "Wat geldt voor type 2 diabetes bij gewichtsverlies?",
                                "Hormonaal stelsel",
                                List.of(a161), null);

                // --- 42 ---
                Antwoorden a165 = new Antwoorden(true, "Tirzepatide is een GLP-1/GIP receptoragonist");
                Vragen vraag42 = new Vragen("connect-choice", "Wat is tirzepatide (Mounjaro)?", "Hormonaal stelsel",
                                List.of(a165),
                                null);

                // --- 43 ---
                Antwoorden a169 = new Antwoorden(true, "Grotere HbA1c-daling dan semaglutide");
                Vragen vraag43 = new Vragen("connect-choice", "Wat toonde de SURPASS-studie aan over tirzepatide?",
                                "Hormonaal stelsel",
                                List.of(a169), null);

                // --- 44 ---
                Antwoorden a173 = new Antwoorden(true, "≥15% gewichtsverlies geeft betere metabole controle");
                Vragen vraag44 = new Vragen("connect-choice", "Wat is het verband tussen gewichtsverlies en HbA1c?",
                                "Hormonaal stelsel",
                                List.of(a173), null);

                // --- 45 ---
                Antwoorden a177 = new Antwoorden(true, "Orforglipron is een niet-peptide orale GLP-1 RA");
                Vragen vraag45 = new Vragen("connect-choice", "Wat is orforglipron?", "Hormonaal stelsel",
                                List.of(a177), null);

                // --- 46 ---
                Antwoorden a181 = new Antwoorden(true, "GLP-1, GIP en glucagon receptoragonist");
                Vragen vraag46 = new Vragen("connect-choice", "Wat is het werkingsmechanisme van retatrutide?",
                                "Hormonaal stelsel",
                                List.of(a181), null);

                // --- 47 ---
                Antwoorden a185 = new Antwoorden(true, "HbA1c > 7.5% en BMI ≥ 30 kg/m²");
                Vragen vraag47 = new Vragen("connect-choice",
                                "Wat zijn de Belgische terugbetalingsvoorwaarden voor GLP-1 RA?",
                                "GGG: Goed geneesmiddelengebruik",
                                List.of(a185), null);

                // --- 48 ---
                Antwoorden a189 = new Antwoorden(true, "HbA1c tussen 7% en 9%");
                Vragen vraag48 = new Vragen("connect-choice",
                                "Wat is de HbA1c-voorwaarde voor SGLT2-inhibitor terugbetaling in België?",
                                "GGG: Goed geneesmiddelengebruik",
                                List.of(a189), null);

                // --- 49 ---
                Antwoorden a193 = new Antwoorden(true, "Een eGFR > 60 ml/min");
                Vragen vraag49 = new Vragen("connect-choice",
                                "Wat is de eGFR-voorwaarde voor gebruik van SGLT2-inhibitoren in België?",
                                "GGG: Goed geneesmiddelengebruik",
                                List.of(a193), null);

                // --- 50 ---
                Antwoorden a197 = new Antwoorden(true, "Maximaal 4 educatiesessies per jaar van 30 minuten");
                Vragen vraag50 = new Vragen("connect-choice",
                                "Wat voorziet het Belgische starttraject diabetes inzake educatie?",
                                "GGG: Goed geneesmiddelengebruik",
                                List.of(a197), null);

                // // --- 21 ---
                // Antwoorden a81 = new Antwoorden(true, "Misselijkheid en diarree, meestal
                // tijdelijk");
                // Antwoorden a82 = new Antwoorden(false, "Hoge koorts en keelpijn");
                // Antwoorden a83 = new Antwoorden(false, "Sterke toename van eetlust");
                // Antwoorden a84 = new Antwoorden(false, "Daling van de bloeddruk");
                // Vragen vraag21 = new Vragen("hangman",
                // "Wat zijn de meest voorkomende bijwerkingen bij de start van metformine?",
                // List.of(a81, a82, a83, a84),
                // null);

                // // --- 22 ---
                // Antwoorden a85 = new Antwoorden(true, "Tijdens of net na de maaltijd
                // innemen");
                // Antwoorden a86 = new Antwoorden(false, "Op een lege maag met een glas melk");
                // Antwoorden a87 = new Antwoorden(false, "Altijd ’s avonds voor het
                // slapengaan");
                // Antwoorden a88 = new Antwoorden(false, "Altijd gecombineerd met insuline");
                // Vragen vraag22 = new Vragen("hangman", "Wat is de aanbevolen manier om
                // metformine in te nemen?",
                // List.of(a85, a86, a87, a88), null);

                // // --- 23 ---
                // Antwoorden a89 = new Antwoorden(true, "Stimuleert insulinesecretie uit de
                // β-cellen");
                // Antwoorden a90 = new Antwoorden(false, "Verlaagt de glucoseabsorptie in de
                // darm");
                // Antwoorden a91 = new Antwoorden(false, "Remt de glucagonproductie");
                // Antwoorden a92 = new Antwoorden(false, "Vertraagt de maaglediging");
                // Vragen vraag23 = new Vragen("hangman", "Wat is het werkingsmechanisme van
                // sulfonylurea?",
                // List.of(a89, a90, a91, a92), null);

                // // --- 24 ---
                // Antwoorden a93 = new Antwoorden(true, "Hypoglycemie en gewichtstoename");
                // Antwoorden a94 = new Antwoorden(false, "Hoofdpijn en hyperventilatie");
                // Antwoorden a95 = new Antwoorden(false, "Nierkolieken en diarree");
                // Antwoorden a96 = new Antwoorden(false, "Koorts en huiduitslag");
                // Vragen vraag24 = new Vragen("hangman", "Wat zijn typische nevenwerkingen van
                // sulfonylurea?",
                // List.of(a93, a94, a95, a96), null);

                // // --- 25 ---
                // Antwoorden a97 = new Antwoorden(true, "Niet gebruiken bij GFR < 30 ml/min");
                // Antwoorden a98 = new Antwoorden(false, "Aan te raden bij
                // leverinsufficiëntie");
                // Antwoorden a99 = new Antwoorden(false, "Veilig tijdens zwangerschap");
                // Antwoorden a100 = new Antwoorden(false, "Mag gecombineerd met GLP-1
                // agonisten");
                // Vragen vraag25 = new Vragen("hangman", "Wat is een belangrijke
                // contra-indicatie voor sulfonylurea?",
                // List.of(a97, a98, a99, a100), null);

                // // --- 26 ---
                // Antwoorden a101 = new Antwoorden(true, "Verlaagt HbA1c gemiddeld met 0,6% tot
                // 2%");
                // Antwoorden a102 = new Antwoorden(false, "Verhoogt HbA1c gemiddeld met 1%");
                // Antwoorden a103 = new Antwoorden(false, "Heeft geen effect op HbA1c");
                // Antwoorden a104 = new Antwoorden(false, "Verhoogt het risico op hypoglycemie
                // sterk");
                // Vragen vraag26 = new Vragen("hangman", "Wat is het effect van DPP-4
                // inhibitoren op HbA1c?",
                // List.of(a101, a102, a103, a104), null);

                // // --- 27 ---
                // Antwoorden a105 = new Antwoorden(true, "Cardiorenaal veilig en
                // gewichtsneutraal");
                // Antwoorden a106 = new Antwoorden(false, "Sterk gewichtverhogend");
                // Antwoorden a107 = new Antwoorden(false, "Niet veilig bij ouderen");
                // Antwoorden a108 = new Antwoorden(false, "Leidt vaak tot hypoglycemie");
                // Vragen vraag27 = new Vragen("hangman", "Wat is een voordeel van DPP-4
                // inhibitoren?",
                // List.of(a105, a106, a107, a108), null);

                // // --- 28 ---
                // Antwoorden a109 = new Antwoorden(true, "Vermindert gewicht en bloeddruk");
                // Antwoorden a110 = new Antwoorden(false, "Verhoogt de eetlust");
                // Antwoorden a111 = new Antwoorden(false, "Verhoogt LDL-cholesterol");
                // Antwoorden a112 = new Antwoorden(false, "Verhoogt hartfrequentie zonder
                // nut");
                // Vragen vraag28 = new Vragen("hangman", "Wat is een bijkomend gunstig effect
                // van GLP-1 receptoragonisten?",
                // List.of(a109, a110, a111, a112), null);

                // // --- 29 ---
                // Antwoorden a113 = new Antwoorden(true, "GLP-1 RA heeft cardiovasculaire
                // voordelen");
                // Antwoorden a114 = new Antwoorden(false, "GLP-1 RA verhoogt risico op
                // infarct");
                // Antwoorden a115 = new Antwoorden(false, "GLP-1 RA heeft geen invloed op
                // hartziekten");
                // Antwoorden a116 = new Antwoorden(false, "GLP-1 RA verlaagt enkel bloeddruk");
                // Vragen vraag29 = new Vragen("hangman", "Wat tonen studies aan over GLP-1 RA
                // en cardiovasculair risico?",
                // List.of(a113, a114, a115, a116), null);

                // // --- 30 ---
                // Antwoorden a117 = new Antwoorden(true, "Minder dan 5 dagen: alsnog
                // toedienen");
                // Antwoorden a118 = new Antwoorden(false, "Meer dan 2 dagen: dubbele dosis
                // zetten");
                // Antwoorden a119 = new Antwoorden(false, "Altijd wachten tot volgende week");
                // Antwoorden a120 = new Antwoorconst apiUrl = 'http://10.0.2.2:8080'den(false,
                // "Altijd contact opnemen met spoedarts");
                // Vragen vraag30 = new Vragen("hangman", "Wat doe je bij een gemiste Ozempic®
                // injectie?",
                // List.of(a117, a118, a119, a120), null);

                // // --- 31 ---
                // Antwoorden a121 = new Antwoorden(true, "Minstens 30 minuten voor het
                // ontbijt");
                // Antwoorden a122 = new Antwoorden(false, "Direct na het ontbijt");
                // Antwoorden a123 = new Antwoorden(false, "Voor het slapengaan met melk");
                // Antwoorden a124 = new Antwoorden(false, "Na de lunch");
                // Vragen vraag31 = new Vragen("connect-choice", "Wanneer moet Rybelsus® (orale
                // semaglutide) worden ingenomen?",
                // List.of(a121, a122, a123, a124), null);

                // // --- 32 ---
                // Antwoorden a125 = new Antwoorden(true, "Met maximaal 120 ml water
                // doorslikken");
                // Antwoorden a126 = new Antwoorden(false, "Met minstens 500 ml water");
                // Antwoorden a127 = new Antwoorden(false, "Met vruchtensap voor betere
                // opname");
                // Antwoorden a128 = new Antwoorden(false, "Tablet eerst breken voor opname");
                // Vragen vraag32 = new Vragen("connect-choice", "Hoe moet Rybelsus® worden
                // ingenomen volgens voorschrift?",
                // List.of(a125, a126, a127, a128), null);

                // // --- 33 ---
                // Antwoorden a129 = new Antwoorden(true, "Wachten tot 30 minuten na inname om
                // te eten of drinken");
                // Antwoorden a130 = new Antwoorden(false, "Onmiddellijk ontbijten na inname");
                // Antwoorden a131 = new Antwoorden(false, "Minstens 10 minuten wachten");
                // Antwoorden a132 = new Antwoorden(false, "Minstens 2 uur vasten voor inname");
                // Vragen vraag33 = new Vragen("connect-choice", "Wat is belangrijk na inname
                // van orale semaglutide (Rybelsus®)?",
                // List.of(a129, a130, a131, a132), null);

                // // --- 34 ---
                // Antwoorden a133 = new Antwoorden(true, "Misselijkheid en braken");
                // Antwoorden a134 = new Antwoorden(false, "Slaperigheid en spierpijn");
                // Antwoorden a135 = new Antwoorden(false, "Hyperventilatie en tachycardie");
                // Antwoorden a136 = new Antwoorden(false, "Hoofdpijn en diarree");
                // Vragen vraag34 = new Vragen("connect-choice",
                // "Wat is de meest voorkomende nevenwerking van GLP-1 receptoragonisten?",
                // List.of(a133, a134, a135, a136), null);

                // // --- 35 ---
                // Antwoorden a137 = new Antwoorden(true, "Kleine porties en vetarme
                // maaltijden");
                // Antwoorden a138 = new Antwoorden(false, "Zoutrijke voeding en veel
                // frisdrank");
                // Antwoorden a139 = new Antwoorden(false, "Koolhydraatrijke snacks");
                // Antwoorden a140 = new Antwoorden(false, "Koffie en alcohol bij elke
                // maaltijd");
                // Vragen vraag35 = new Vragen("connect-choice",
                // "Welke voedingsadviezen helpen bij GLP-1-gerelateerde misselijkheid?",
                // List.of(a137, a138, a139, a140),
                // null);

                // // --- 36 ---
                // Antwoorden a141 = new Antwoorden(true, "≥150 minuten matige activiteit per
                // week");
                // Antwoorden a142 = new Antwoorden(false, "30 minuten per maand");
                // Antwoorden a143 = new Antwoorden(false, "Alleen krachttraining");
                // Antwoorden a144 = new Antwoorden(false, "Enkel wandelen bij nuchtere
                // glycemie");
                // Vragen vraag36 = new Vragen("connect-choice",
                // "Wat is de aanbevolen hoeveelheid fysieke activiteit per week bij diabetes?",
                // List.of(a141, a142, a143, a144), null);

                // // --- 37 ---
                // Antwoorden a145 = new Antwoorden(true, "5-10% gewichtsverlies verlaagt
                // cardiovasculair risico");
                // Antwoorden a146 = new Antwoorden(false, "10% gewichtstoename verlaagt
                // risico");
                // Antwoorden a147 = new Antwoorden(false, "Gewicht heeft geen invloed op
                // CV-risico");
                // Antwoorden a148 = new Antwoorden(false, "Gewichtsverlies verhoogt
                // LDL-cholesterol");
                // Vragen vraag37 = new Vragen("connect-choice",
                // "Wat is het effect van gewichtsverlies op cardiovasculaire risico’s bij
                // diabetes?",
                // List.of(a145, a146, a147, a148), null);

                // // --- 38 ---
                // Antwoorden a149 = new Antwoorden(true,
                // "Hart- en vaatziekten zijn oorzaak van 60–80% van de sterfte bij diabetes");
                // Antwoorden a150 = new Antwoorden(false, "Hart- en vaatziekten spelen zelden
                // een rol bij diabetes");
                // Antwoorden a151 = new Antwoorden(false, "Leverziekten zijn de belangrijkste
                // doodsoorzaak");
                // Antwoorden a152 = new Antwoorden(false, "Diabetes vermindert het risico op
                // infarct");
                // Vragen vraag38 = new Vragen("connect-choice", "Wat is de belangrijkste
                // doodsoorzaak bij diabetespatiënten?",
                // List.of(a149, a150, a151, a152), null);

                // // --- 39 ---
                // Antwoorden a153 = new Antwoorden(true, "SGLT2-remmers verminderen
                // ziekenhuisopnames voor hartfalen");
                // Antwoorden a154 = new Antwoorden(false, "SGLT2-remmers verhogen risico op
                // hartfalen");
                // Antwoorden a155 = new Antwoorden(false, "SGLT2-remmers beïnvloeden het hart
                // niet");
                // Antwoorden a156 = new Antwoorden(false, "SGLT2-remmers veroorzaken
                // hypertensie");
                // Vragen vraag39 = new Vragen("connect-choice", "Wat is het effect van
                // SGLT2-inhibitoren op hartfalen?",
                // List.of(a153, a154, a155, a156), null);

                // // --- 40 ---
                // Antwoorden a157 = new Antwoorden(true, "Chronische nierschade");
                // Antwoorden a158 = new Antwoorden(false, "Astma");
                // Antwoorden a159 = new Antwoorden(false, "Leverfibrose");
                // Antwoorden a160 = new Antwoorden(false, "Maagzweren");
                // Vragen vraag40 = new Vragen("connect-choice", "Welke complicatie treedt vaak
                // op bij diabetes en beïnvloedt eGFR?",
                // List.of(a157, a158, a159, a160), null);

                // // --- 41 ---
                // Antwoorden a161 = new Antwoorden(true, "Reversie mogelijk bij vroegtijdige
                // gewichtsreductie");
                // Antwoorden a162 = new Antwoorden(false, "Altijd onomkeerbaar");
                // Antwoorden a163 = new Antwoorden(false, "Enkel omkeerbaar met bariatrische
                // chirurgie");
                // Antwoorden a164 = new Antwoorden(false, "Nooit klinisch relevant");
                // Vragen vraag41 = new Vragen("connect-choice", "Wat geldt voor diabetes type 2
                // bij gewichtsverlies?",
                // List.of(a161, a162, a163, a164), null);

                // // --- 42 ---
                // Antwoorden a165 = new Antwoorden(true, "Tirzepatide is een GLP-1/GIP
                // receptoragonist");
                // Antwoorden a166 = new Antwoorden(false, "Tirzepatide is een insuline-analoge
                // spray");
                // Antwoorden a167 = new Antwoorden(false, "Tirzepatide is een orale
                // SGLT2-remmer");
                // Antwoorden a168 = new Antwoorden(false, "Tirzepatide is een DPP-4 remmer");
                // Vragen vraag42 = new Vragen("connect-choice", "Wat is tirzepatide
                // (Mounjaro)?", List.of(a165, a166, a167, a168),
                // null);

                // // --- 43 ---
                // Antwoorden a169 = new Antwoorden(true, "Grotere HbA1c-daling dan
                // semaglutide");
                // Antwoorden a170 = new Antwoorden(false, "Kleinere HbA1c-daling dan
                // metformine");
                // Antwoorden a171 = new Antwoorden(false, "Geen effect op gewicht");
                // Antwoorden a172 = new Antwoorden(false, "Enkel effect bij diabetes type 1");
                // Vragen vraag43 = new Vragen("connect-choice", "Wat toonde de SURPASS-studie
                // aan over tirzepatide?",
                // List.of(a169, a170, a171, a172), null);

                // // --- 44 ---
                // Antwoorden a173 = new Antwoorden(true, "≥15% gewichtsverlies geeft betere
                // metabole controle");
                // Antwoorden a174 = new Antwoorden(false, "Gewichtsverlies heeft geen effect op
                // HbA1c");
                // Antwoorden a175 = new Antwoorden(false, "Gewichtsverlies is enkel
                // cosmetisch");
                // Antwoorden a176 = new Antwoorden(false, "Gewichtsverlies vermindert
                // spiermassa zonder nut");
                // Vragen vraag44 = new Vragen("connect-choice", "Wat is het verband tussen
                // gewichtsverlies en HbA1c?",
                // List.of(a173, a174, a175, a176), null);

                // // --- 45 ---
                // Antwoorden a177 = new Antwoorden(true, "Orforglipron is een niet-peptide
                // orale GLP-1 RA");
                // Antwoorden a178 = new Antwoorden(false, "Orforglipron is een insulinepomp");
                // Antwoorden a179 = new Antwoorden(false, "Orforglipron is een vetzuurremmer");
                // Antwoorden a180 = new Antwoorden(false, "Orforglipron is een DPP-4 remmer");
                // Vragen vraag45 = new Vragen("connect-choice", "Wat is orforglipron?",
                // List.of(a177, a178, a179, a180), null);

                // // --- 46 ---
                // Antwoorden a181 = new Antwoorden(true, "GLP-1, GIP en glucagon
                // receptoragonist");
                // Antwoorden a182 = new Antwoorden(false, "Alleen GLP-1 receptoragonist");
                // Antwoorden a183 = new Antwoorden(false, "Alleen DPP-4 inhibitor");
                // Antwoorden a184 = new Antwoorden(false, "SGLT2-inhibitor");
                // Vragen vraag46 = new Vragen("connect-choice", "Wat is het werkingsmechanisme
                // van retatrutide?",
                // List.of(a181, a182, a183, a184), null);

                // // --- 47 ---
                // Antwoorden a185 = new Antwoorden(true, "HbA1c > 7.5% en BMI ≥ 30 kg/m²");
                // Antwoorden a186 = new Antwoorden(false, "HbA1c < 6.5% en BMI ≥ 25 kg/m²");
                // Antwoorden a187 = new Antwoorden(false, "HbA1c > 9% en BMI ≥ 35 kg/m²");
                // Antwoorden a188 = new Antwoorden(false, "HbA1c tussen 5 en 6%");
                // Vragen vraag47 = new Vragen("connect-choice", "Wat zijn de Belgische
                // terugbetalingsvoorwaarden voor GLP-1 RA?",
                // List.of(a185, a186, a187, a188), null);

                // // --- 48 ---
                // Antwoorden a189 = new Antwoorden(true, "HbA1c tussen 7% en 9%");
                // Antwoorden a190 = new Antwoorden(false, "HbA1c > 10%");
                // Antwoorden a191 = new Antwoorden(false, "HbA1c < 6%");
                // Antwoorden a192 = new Antwoorden(false, "Geen HbA1c-voorwaarde");
                // Vragen vraag48 = new Vragen("connect-choice",
                // "Wat is de HbA1c-voorwaarde voor SGLT2-inhibitor terugbetaling in België?",
                // List.of(a189, a190, a191, a192), null);

                // // --- 49 ---
                // Antwoorden a193 = new Antwoorden(true, "Een eGFR > 60 ml/min");
                // Antwoorden a194 = new Antwoorden(false, "Een eGFR < 30 ml/min");
                // Antwoorden a195 = new Antwoorden(false, "Een HbA1c < 6%");
                // Antwoorden a196 = new Antwoorden(false, "Een BMI < 25 kg/m²");
                // Vragen vraag49 = new Vragen("connect-choice",
                // "Wat is de eGFR-voorwaarde voor gebruik van SGLT2-inhibitoren in België?",
                // List.of(a193, a194, a195, a196), null);

                // // --- 50 ---
                // Antwoorden a197 = new Antwoorden(true, "Maximaal 4 educatiesessies per jaar
                // van 30 minuten");
                // Antwoorden a198 = new Antwoorden(false, "1 educatie per maand verplicht");
                // Antwoorden a199 = new Antwoorden(false, "Educatie enkel door arts");
                // Antwoorden a200 = new Antwoorden(false, "Educatie niet terugbetaald in
                // België");
                // Vragen vraag50 = new Vragen("connect-choice", "Wat voorziet het Belgische
                // starttraject diabetes inzake educatie?",
                // List.of(a197, a198, a199, a200), null);

                vragenRepository.save(vraag1);
                vragenRepository.save(vraag2);
                vragenRepository.save(vraag3);
                vragenRepository.save(vraag4);
                vragenRepository.save(vraag5);
                vragenRepository.save(vraag6);
                vragenRepository.save(vraag7);
                vragenRepository.save(vraag8);
                vragenRepository.save(vraag9);
                vragenRepository.save(vraag10);
                vragenRepository.save(vraag11);
                vragenRepository.save(vraag12);
                vragenRepository.save(vraag13);
                vragenRepository.save(vraag14);
                vragenRepository.save(vraag15);
                vragenRepository.save(vraag16);
                vragenRepository.save(vraag17);
                vragenRepository.save(vraag18);
                vragenRepository.save(vraag19);
                vragenRepository.save(vraag20);
                vragenRepository.save(vraag21);
                vragenRepository.save(vraag22);
                vragenRepository.save(vraag23);
                vragenRepository.save(vraag24);
                vragenRepository.save(vraag25);
                vragenRepository.save(vraag26);
                // vragenRepository.save(vraag27);
                vragenRepository.save(vraag28);
                // vragenRepository.save(vraag29);
                // vragenRepository.save(vraag30);
                vragenRepository.save(vraag31);
                vragenRepository.save(vraag32);
                vragenRepository.save(vraag33);
                vragenRepository.save(vraag34);
                vragenRepository.save(vraag35);
                vragenRepository.save(vraag36);
                vragenRepository.save(vraag37);
                vragenRepository.save(vraag38);
                vragenRepository.save(vraag39);
                vragenRepository.save(vraag40);
                vragenRepository.save(vraag41);
                vragenRepository.save(vraag42);
                vragenRepository.save(vraag43);
                vragenRepository.save(vraag44);
                vragenRepository.save(vraag45);
                vragenRepository.save(vraag46);
                vragenRepository.save(vraag47);
                vragenRepository.save(vraag48);
                vragenRepository.save(vraag49);
                vragenRepository.save(vraag50);
                // vragenRepository.save(vraag71);
                // vragenRepository.save(vraag72);
                // vragenRepository.save(vraag73);
                // vragenRepository.save(vraag74);
                // vragenRepository.save(vraag75);
                // vragenRepository.save(vraag76);
                // vragenRepository.save(vraag77);
                // vragenRepository.save(vraag78);
                // vragenRepository.save(vraag79);
                // vragenRepository.save(vraag80);

                // Now create user and result (linking them after question exists)
                User user1 = new User("Henk", "henk@hotmail.com", "henkie", 0, passwordEncoder.encode("henk"),
                                User.Role.USER, null);
                userRepository.save(user1); // persist user to get an ID

                User user2 = new User("Admin", "admin@hotmail.com", "admin", 0, passwordEncoder.encode("admin"),
                                User.Role.ADMIN, null);
                userRepository.save(user2); // persist user to get an ID

                Resultaat r1 = new Resultaat("75%");
                r1.setUser(user1);
                r1.setVraag(vraag3);

                // Save resultaat (this will link user + vraag)
                resultaatRepository.save(r1);

                // Initialize FAQ data
                Faq faq1 = new Faq("Is deze app betalend?", "ja deze app is betalend");
                Faq faq2 = new Faq("Hoe kan ik mijn wachtwoord resetten?",
                                "Klik op \"wachtwoord vergeten\" op de loginpagina");
                Faq faq3 = new Faq("Kan ik de app offline gebruiken?",
                                "Nee, je hebt een internetverbinding nodig om de quiz te spelen");

                faqRepository.save(faq1);
                faqRepository.save(faq2);
                faqRepository.save(faq3);
        };
}
