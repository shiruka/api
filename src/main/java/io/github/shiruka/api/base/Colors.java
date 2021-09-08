package io.github.shiruka.api.base;

import java.util.Locale;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

/**
 * an enum class that contains custom colors.
 */
@Accessors(fluent = true)
public enum Colors {
  Black("000000"),
  Navy_Blue("000080"),
  Dark_Blue("0000C8"),
  Blue("0000FF"),
  Stratos("000741"),
  Swamp("001B1C"),
  Resolution_Blue("002387"),
  Deep_Fir("002900"),
  Burnham("002E20"),
  International_Klein_Blue("002FA7"),
  Prussian_Blue("003153"),
  Midnight_Blue("003366"),
  Smalt("003399"),
  Deep_Teal("003532"),
  Cyprus("003E40"),
  Kaitoke_Green("004620"),
  Cobalt("0047AB"),
  Crusoe("004816"),
  Sherpa_Blue("004950"),
  Endeavour("0056A7"),
  Camarone("00581A"),
  Science_Blue("0066CC"),
  Blue_Ribbon("0066FF"),
  Tropical_Rain_Forest("00755E"),
  Allports("0076A3"),
  Deep_Cerulean("007BA7"),
  Lochmara("007EC7"),
  Azure_Radiance("007FFF"),
  Teal("008080"),
  Bondi_Blue("0095B6"),
  Pacific_Blue("009DC4"),
  Persian_Green("00A693"),
  Jade("00A86B"),
  Caribbean_Green("00CC99"),
  Robins_Egg_Blue("00CCCC"),
  Green("00FF00"),
  Spring_Green("00FF7F"),
  Cyan("00FFFF"),
  Blue_Charcoal("010D1A"),
  Midnight("011635"),
  Holly("011D13"),
  Daintree("012731"),
  Cardin_Green("01361C"),
  County_Green("01371A"),
  Astronaut_Blue("013E62"),
  Regal_Blue("013F6A"),
  Aqua_Deep("014B43"),
  Orient("015E85"),
  Blue_Stone("016162"),
  Fun_Green("016D39"),
  Pine_Green("01796F"),
  Blue_Lagoon("017987"),
  Deep_Sea("01826B"),
  Green_Haze("01A368"),
  English_Holly("022D15"),
  Sherwood_Green("02402C"),
  Congress_Blue("02478E"),
  Evening_Sea("024E46"),
  Bahama_Blue("026395"),
  Observatory("02866F"),
  Cerulean("02A4D3"),
  Tangaroa("03163C"),
  Green_Vogue("032B52"),
  Mosque("036A6E"),
  Midnight_Moss("041004"),
  Black_Pearl("041322"),
  Blue_Whale("042E4C"),
  Zuccini("044022"),
  Teal_Blue("044259"),
  Deep_Cove("051040"),
  Gulf_Blue("051657"),
  Venice_Blue("055989"),
  Watercourse("056F57"),
  Catalina_Blue("062A78"),
  Tiber("063537"),
  Gossamer("069B81"),
  Niagara("06A189"),
  Tarawera("073A50"),
  Jaguar("080110"),
  Black_Bean("081910"),
  Deep_Sapphire("082567"),
  Elf_Green("088370"),
  Bright_Turquoise("08E8DE"),
  Downriver("092256"),
  Palm_Green("09230F"),
  Madison("09255D"),
  Bottle_Green("093624"),
  Deep_Sea_Green("095859"),
  Salem("097F4B"),
  Black_Russian("0A001C"),
  Dark_Fern("0A480D"),
  Japanese_Laurel("0A6906"),
  Atoll("0A6F75"),
  Cod_Gray("0B0B0B"),
  Marshland("0B0F08"),
  Gordons_Green("0B1107"),
  Black_Forest("0B1304"),
  San_Felix("0B6207"),
  Malachite("0BDA51"),
  Ebony("0C0B1D"),
  Woodsmoke("0C0D0F"),
  Racing_Green("0C1911"),
  Surfie_Green("0C7A79"),
  Blue_Chill("0C8990"),
  Black_Rock("0D0332"),
  Bunker("0D1117"),
  Aztec("0D1C19"),
  Bush("0D2E1C"),
  Cinder("0E0E18"),
  Firefly("0E2A30"),
  Torea_Bay("0F2D9E"),
  Vulcan("10121D"),
  Green_Waterloo("101405"),
  Eden("105852"),
  Arapawa("110C6C"),
  Ultramarine("120A8F"),
  Elephant("123447"),
  Jewel("126B40"),
  Diesel("130000"),
  Asphalt("130A06"),
  Blue_Zodiac("13264D"),
  Parsley("134F19"),
  Nero("140600"),
  Tory_Blue("1450AA"),
  Bunting("151F4C"),
  Denim("1560BD"),
  Genoa("15736B"),
  Mirage("161928"),
  Hunter_Green("161D10"),
  Big_Stone("162A40"),
  Celtic("163222"),
  Timber_Green("16322C"),
  Gable_Green("163531"),
  Pine_Tree("171F04"),
  Chathams_Blue("175579"),
  Deep_Forest_Green("182D09"),
  Blumine("18587A"),
  Palm_Leaf("19330E"),
  Nile_Blue("193751"),
  Fun_Blue("1959A8"),
  Lucky_Point("1A1A68"),
  Mountain_Meadow("1AB385"),
  Tolopea("1B0245"),
  Haiti("1B1035"),
  Deep_Koamaru("1B127B"),
  Acadia("1B1404"),
  Seaweed("1B2F11"),
  Biscay("1B3162"),
  Matisse("1B659D"),
  Crowshead("1C1208"),
  Rangoon_Green("1C1E13"),
  Persian_Blue("1C39BB"),
  Everglade("1C402E"),
  Elm("1C7C7D"),
  Green_Pea("1D6142"),
  Creole("1E0F04"),
  Karaka("1E1609"),
  El_Paso("1E1708"),
  Cello("1E385B"),
  Te_Papa_Green("1E433C"),
  Dodger_Blue("1E90FF"),
  Eastern_Blue("1E9AB0"),
  Night_Rider("1F120F"),
  Java("1FC2C2"),
  Jacksons_Purple("20208D"),
  Cloud_Burst("202E54"),
  Blue_Dianne("204852"),
  Eternity("211A0E"),
  Deep_Blue("220878"),
  Forest_Green("228B22"),
  Mallard("233418"),
  Violet("240A40"),
  Kilamanjaro("240C02"),
  Log_Cabin("242A1D"),
  Black_Olive("242E16"),
  Green_House("24500F"),
  Graphite("251607"),
  Cannon_Black("251706"),
  Port_Gore("251F4F"),
  Shark("25272C"),
  Green_Kelp("25311C"),
  Curious_Blue("2596D1"),
  Paua("260368"),
  Paris_M("26056A"),
  Wood_Bark("261105"),
  Gondola("261414"),
  Steel_Gray("262335"),
  Ebony_Clay("26283B"),
  Bay_of_Many("273A81"),
  Plantation("27504B"),
  Eucalyptus("278A5B"),
  Oil("281E15"),
  Astronaut("283A77"),
  Mariner("286ACD"),
  Violent_Violet("290C5E"),
  Bastille("292130"),
  Zeus("292319"),
  Charade("292937"),
  Jelly_Bean("297B9A"),
  Jungle_Green("29AB87"),
  Cherry_Pie("2A0359"),
  Coffee_Bean("2A140E"),
  Baltic_Sea("2A2630"),
  Turtle_Green("2A380B"),
  Cerulean_Blue("2A52BE"),
  Sepia_Black("2B0202"),
  Valhalla("2B194F"),
  Heavy_Metal("2B3228"),
  Blue_Gem("2C0E8C"),
  Revolver("2C1632"),
  Bleached_Cedar("2C2133"),
  Lochinvar("2C8C84"),
  Mikado("2D2510"),
  Outer_Space("2D383A"),
  St_Tropaz("2D569B"),
  Jacaranda("2E0329"),
  Jacko_Bean("2E1905"),
  Rangitoto("2E3222"),
  Rhino("2E3F62"),
  Sea_Green("2E8B57"),
  Scooter("2EBFD4"),
  Onion("2F270E"),
  Governor_Bay("2F3CB3"),
  Sapphire("2F519E"),
  Spectra("2F5A57"),
  Casal("2F6168"),
  Melanzane("300529"),
  Cocoa_Brown("301F1E"),
  Woodrush("302A0F"),
  San_Juan("304B6A"),
  Turquoise("30D5C8"),
  Eclipse("311C17"),
  Pickled_Bluewood("314459"),
  Azure("315BA1"),
  Calypso("31728D"),
  Paradiso("317D82"),
  Persian_Indigo("32127A"),
  Blackcurrant("32293A"),
  Mine_Shaft("323232"),
  Stromboli("325D52"),
  Bilbao("327C14"),
  Astral("327DA0"),
  Christalle("33036B"),
  Thunder("33292F"),
  Shamrock("33CC99"),
  Tamarind("341515"),
  Mardi_Gras("350036"),
  Valentino("350E42"),
  Jagger("350E57"),
  Tuna("353542"),
  Chambray("354E8C"),
  Martinique("363050"),
  Tuatara("363534"),
  Waiouru("363C0D"),
  Ming("36747D"),
  La_Palma("368716"),
  Chocolate("370202"),
  Clinker("371D09"),
  Brown_Tumbleweed("37290E"),
  Birch("373021"),
  Oracle("377475"),
  Blue_Diamond("380474"),
  Grape("381A51"),
  Dune("383533"),
  Oxford_Blue("384555"),
  Clover("384910"),
  Limed_Spruce("394851"),
  Dell("396413"),
  Toledo("3A0020"),
  Sambuca("3A2010"),
  Jacarta("3A2A6A"),
  William("3A686C"),
  Killarney("3A6A47"),
  Keppel("3AB09E"),
  Temptress("3B000B"),
  Aubergine("3B0910"),
  Jon("3B1F1F"),
  Treehouse("3B2820"),
  Amazon("3B7A57"),
  Boston_Blue("3B91B4"),
  Windsor("3C0878"),
  Rebel("3C1206"),
  Meteorite("3C1F76"),
  Dark_Ebony("3C2005"),
  Camouflage("3C3910"),
  Bright_Gray("3C4151"),
  Cape_Cod("3C4443"),
  Lunar_Green("3C493A"),
  Bean_("3D0C02"),
  Bistre("3D2B1F"),
  Goblin("3D7D52"),
  Kingfisher_Daisy("3E0480"),
  Cedar("3E1C14"),
  English_Walnut("3E2B23"),
  Black_Marlin("3E2C1C"),
  Ship_Gray("3E3A44"),
  Pelorous("3EABBF"),
  Bronze("3F2109"),
  Cola("3F2500"),
  Madras("3F3002"),
  Minsk("3F307F"),
  Cabbage_Pont("3F4C3A"),
  Tom_Thumb("3F583B"),
  Mineral_Green("3F5D53"),
  Puerto_Rico("3FC1AA"),
  Harlequin("3FFF00"),
  Brown_Pod("401801"),
  Cork("40291D"),
  Masala("403B38"),
  Thatch_Green("403D19"),
  Fiord("405169"),
  Viridian("40826D"),
  Chateau_Green("40A860"),
  Ripe_Plum("410056"),
  Paco("411F10"),
  Deep_Oak("412010"),
  Merlin("413C37"),
  Gun_Powder("414257"),
  East_Bay("414C7D"),
  Royal_Blue("4169E1"),
  Ocean_Green("41AA78"),
  Burnt_Maroon("420303"),
  Lisbon_Brown("423921"),
  Faded_Jade("427977"),
  Scarlet_Gum("431560"),
  Iroko("433120"),
  Armadillo("433E37"),
  River_Bed("434C59"),
  Green_Leaf("436A0D"),
  Barossa("44012D"),
  Morocco_Brown("441D00"),
  Mako("444954"),
  Kelp("454936"),
  San_Marino("456CAC"),
  Picton_Blue("45B1E8"),
  Loulou("460B41"),
  Crater_Brown("462425"),
  Gray_Asparagus("465945"),
  Steel_Blue("4682B4"),
  Rustic_Red("480404"),
  Bulgarian_Rose("480607"),
  Clairvoyant("480656"),
  Cocoa_Bean("481C1C"),
  Woody_Brown("483131"),
  Taupe("483C32"),
  Van_Cleef("49170C"),
  Brown_Derby("492615"),
  Metallic_Bronze("49371B"),
  Verdun_Green("495400"),
  Blue_Bayoux("496679"),
  Bismark("497183"),
  Bracken("4A2A04"),
  Deep_Bronze("4A3004"),
  Mondo("4A3C30"),
  Tundora("4A4244"),
  Gravel("4A444B"),
  Trout("4A4E5A"),
  Pigment_Indigo("4B0082"),
  Nandor("4B5D52"),
  Saddle("4C3024"),
  Abbey("4C4F56"),
  Blackberry("4D0135"),
  Cab_Sav("4D0A18"),
  Indian_Tan("4D1E01"),
  Cowboy("4D282D"),
  Livid_Brown("4D282E"),
  Rock("4D3833"),
  Punga("4D3D14"),
  Bronzetone("4D400F"),
  Woodland("4D5328"),
  Mahogany("4E0606"),
  Bossanova("4E2A5A"),
  Matterhorn("4E3B41"),
  Bronze_Olive("4E420C"),
  Mulled_Wine("4E4562"),
  Axolotl("4E6649"),
  Wedgewood("4E7F9E"),
  Shakespeare("4EABD1"),
  Honey_Flower("4F1C70"),
  Daisy_Bush("4F2398"),
  Indigo("4F69C6"),
  Fern_Green("4F7942"),
  Fruit_Salad("4F9D5D"),
  Apple("4FA83D"),
  Mortar("504351"),
  Kashmir_Blue("507096"),
  Cutty_Sark("507672"),
  Emerald("50C878"),
  Emperor("514649"),
  Chalet_Green("516E3D"),
  Como("517C66"),
  Smalt_Blue("51808F"),
  Castro("52001F"),
  Maroon_Oak("520C17"),
  Gigas("523C94"),
  Voodoo("533455"),
  Victoria("534491"),
  Hippie_Green("53824B"),
  Heath("541012"),
  Judge_Gray("544333"),
  Fuscous_Gray("54534D"),
  Vida_Loca("549019"),
  Cioccolato("55280C"),
  Saratoga("555B10"),
  Finlandia("556D56"),
  Havelock_Blue("5590D9"),
  Fountain_Blue("56B4BE"),
  Spring_Leaves("578363"),
  Saddle_Brown("583401"),
  Scarpa_Flow("585562"),
  Cactus("587156"),
  Hippie_Blue("589AAF"),
  Wine_Berry("591D35"),
  Brown_Bramble("592804"),
  Congo_Brown("593737"),
  Millbrook("594433"),
  Waikawa_Gray("5A6E9C"),
  Horizon("5A87A0"),
  Jambalaya("5B3013"),
  Bordeaux("5C0120"),
  Mulberry_Wood("5C0536"),
  Carnaby_Tan("5C2E01"),
  Comet("5C5D75"),
  Redwood("5D1E0F"),
  Don_Juan("5D4C51"),
  Chicago("5D5C58"),
  Verdigris("5D5E37"),
  Dingley("5D7747"),
  Breaker_Bay("5DA19F"),
  Kabul("5E483E"),
  Hemlock("5E5D3B"),
  Irish_Coffee("5F3D26"),
  Mid_Gray("5F5F6E"),
  Shuttle_Gray("5F6672"),
  Aqua_Forest("5FA777"),
  Tradewind("5FB3AC"),
  Horses_Neck("604913"),
  Smoky("605B73"),
  Corduroy("606E68"),
  Danube("6093D1"),
  Espresso("612718"),
  Eggplant("614051"),
  Costa_Del_Sol("615D30"),
  Glade_Green("61845F"),
  Buccaneer("622F30"),
  Quincy("623F2D"),
  Butterfly_Bush("624E9A"),
  West_Coast("625119"),
  Finch("626649"),
  Patina("639A8F"),
  Fern("63B76C"),
  Blue_Violet("6456B7"),
  Dolphin("646077"),
  Storm_Dust("646463"),
  Siam("646A54"),
  Nevada("646E75"),
  Cornflower_Blue("6495ED"),
  Viking("64CCDB"),
  Rosewood("65000B"),
  Cherrywood("651A14"),
  Purple_Heart("652DC1"),
  Fern_Frond("657220"),
  Willow_Grove("65745D"),
  Hoki("65869F"),
  Pompadour("660045"),
  Purple("660099"),
  Tyrian_Purple("66023C"),
  Dark_Tan("661010"),
  Silver_Tree("66B58F"),
  Bright_Green("66FF00"),
  Screamin_Green("66FF66"),
  Black_Rose("67032D"),
  Scampi("675FA6"),
  Ironside_Gray("676662"),
  Viridian_Green("678975"),
  Christi("67A712"),
  Nutmeg_Wood_Finish("683600"),
  Zambezi("685558"),
  Salt_Box("685E6E"),
  Tawny_Port("692545"),
  Finn("692D54"),
  Scorpion("695F62"),
  Lynch("697E9A"),
  Spice("6A442E"),
  Himalaya("6A5D1B"),
  Soya_Bean("6A6051"),
  Hairy_Heath("6B2A14"),
  Royal_Purple("6B3FA0"),
  Shingle_Fawn("6B4E31"),
  Dorado("6B5755"),
  Bermuda_Gray("6B8BA2"),
  Olive_Drab("6B8E23"),
  Eminence("6C3082"),
  Turquoise_Blue("6CDAE7"),
  Lonestar("6D0101"),
  Pine_Cone("6D5E54"),
  Dove_Gray("6D6C6C"),
  Juniper("6D9292"),
  Gothic("6D92A1"),
  Red_Oxide("6E0902"),
  Moccaccino("6E1D14"),
  Pickled_Bean("6E4826"),
  Dallas("6E4B26"),
  Kokoda("6E6D57"),
  Pale_Sky("6E7783"),
  Cafe_Royale("6F440C"),
  Flint("6F6A61"),
  Highland("6F8E63"),
  Limeade("6F9D02"),
  Downy("6FD0C5"),
  Persian_Plum("701C1C"),
  Sepia("704214"),
  Antique_Bronze("704A07"),
  Ferra("704F50"),
  Coffee("706555"),
  Slate_Gray("708090"),
  Cedar_Wood_Finish("711A00"),
  Metallic_Copper("71291D"),
  Affair("714693"),
  Studio("714AB2"),
  Tobacco_Brown("715D47"),
  Yellow_Metal("716338"),
  Peat("716B56"),
  Olivetone("716E10"),
  Storm_Gray("717486"),
  Sirocco("718080"),
  Aquamarine_Blue("71D9E2"),
  Venetian_Red("72010F"),
  Old_Copper("724A2F"),
  Go_Ben("726D4E"),
  Raven("727B89"),
  Seance("731E8F"),
  Raw_Umber("734A12"),
  Kimberly("736C9F"),
  Crocodile("736D58"),
  Crete("737829"),
  Xanadu("738678"),
  Spicy_Mustard("74640D"),
  Limed_Ash("747D63"),
  Rolling_Stone("747D83"),
  Blue_Smoke("748881"),
  Laurel("749378"),
  Mantis("74C365"),
  Russett("755A57"),
  Deluge("7563A8"),
  Cosmic("76395D"),
  Blue_Marguerite("7666C6"),
  Lima("76BD17"),
  Sky_Blue("76D7EA"),
  Dark_Burgundy("770F05"),
  Crown_of_Thorns("771F1F"),
  Walnut("773F1A"),
  Pablo("776F61"),
  Pacifika("778120"),
  Oxley("779E86"),
  Pastel_Green("77DD77"),
  Japanese_Maple("780109"),
  Mocha("782D19"),
  Peanut("782F16"),
  Camouflage_Green("78866B"),
  Wasabi("788A25"),
  Ship_Cove("788BBA"),
  Sea_Nymph("78A39C"),
  Roman_Coffee("795D4C"),
  Old_Lavender("796878"),
  Rum("796989"),
  Fedora("796A78"),
  Sandstone("796D62"),
  Spray("79DEEC"),
  Siren("7A013A"),
  Fuchsia_Blue("7A58C1"),
  Boulder("7A7A7A"),
  Wild_Blue_Yonder("7A89B8"),
  De_York("7AC488"),
  Red_Beech("7B3801"),
  Cinnamon("7B3F00"),
  Yukon_Gold("7B6608"),
  Tapa("7B7874"),
  Waterloo_("7B7C94"),
  Flax_Smoke("7B8265"),
  Amulet("7B9F80"),
  Asparagus("7BA05B"),
  Kenyan_Copper("7C1C05"),
  Pesto("7C7631"),
  Topaz("7C778A"),
  Concord("7C7B7A"),
  Jumbo("7C7B82"),
  Trendy_Green("7C881A"),
  Gumbo("7CA1A6"),
  Acapulco("7CB0A1"),
  Neptune("7CB7BB"),
  Pueblo("7D2C14"),
  Bay_Leaf("7DA98D"),
  Malibu("7DC8F7"),
  Bermuda("7DD8C6"),
  Copper_Canyon("7E3A15"),
  Claret("7F1734"),
  Peru_Tan("7F3A02"),
  Falcon("7F626D"),
  Mobster("7F7589"),
  Moody_Blue("7F76D3"),
  Chartreuse("7FFF00"),
  Aquamarine("7FFFD4"),
  Maroon("800000"),
  Rose_Bud_Cherry("800B47"),
  Falu_Red("801818"),
  Red_Robin("80341F"),
  Vivid_Violet("803790"),
  Russet("80461B"),
  Friar_Gray("807E79"),
  Olive("808000"),
  Gray("808080"),
  Gulf_Stream("80B3AE"),
  Glacier("80B3C4"),
  Seagull("80CCEA"),
  Nutmeg("81422C"),
  Spicy_Pink("816E71"),
  Empress("817377"),
  Spanish_Green("819885"),
  Sand_Dune("826F65"),
  Gunsmoke("828685"),
  Battleship_Gray("828F72"),
  Merlot("831923"),
  Shadow("837050"),
  Chelsea_Cucumber("83AA5D"),
  Monte_Carlo("83D0C6"),
  Plum("843179"),
  Granny_Smith("84A0A0"),
  Chetwode_Blue("8581D9"),
  Bandicoot("858470"),
  Bali_Hai("859FAF"),
  Half_Baked("85C4CC"),
  Red_Devil("860111"),
  Lotus("863C3C"),
  Ironstone("86483C"),
  Bull_Shot("864D1E"),
  Rusty_Nail("86560A"),
  Bitter("868974"),
  Regent_Gray("86949F"),
  Disco("871550"),
  Americano("87756E"),
  Hurricane("877C7B"),
  Oslo_Gray("878D91"),
  Sushi("87AB39"),
  Spicy_Mix("885342"),
  Kumera("886221"),
  Suva_Gray("888387"),
  Avocado("888D65"),
  Camelot("893456"),
  Solid_Pink("893843"),
  Cannon_Pink("894367"),
  Makara("897D6D"),
  Burnt_Umber("8A3324"),
  True_V("8A73D6"),
  Clay_Creek("8A8360"),
  Monsoon("8A8389"),
  Stack("8A8F8A"),
  Jordy_Blue("8AB9F1"),
  Electric_Violet("8B00FF"),
  Monarch("8B0723"),
  Corn_Harvest("8B6B0B"),
  Olive_Haze("8B8470"),
  Schooner("8B847E"),
  Natural_Gray("8B8680"),
  Mantle("8B9C90"),
  Portage("8B9FEE"),
  Envy("8BA690"),
  Cascade("8BA9A5"),
  Riptide("8BE6D8"),
  Cardinal_Pink("8C055E"),
  Mule_Fawn("8C472F"),
  Potters_Clay("8C5738"),
  Trendy_Pink("8C6495"),
  Paprika("8D0226"),
  Sanguine_Brown("8D3D38"),
  Tosca("8D3F3F"),
  Cement("8D7662"),
  Granite_Green("8D8974"),
  Manatee("8D90A1"),
  Polo_Blue("8DA8CC"),
  Red_Berry("8E0000"),
  Rope("8E4D1E"),
  Opium("8E6F70"),
  Domino("8E775E"),
  Mamba("8E8190"),
  Nepal("8EABC1"),
  Pohutukawa("8F021C"),
  El_Salva("8F3E33"),
  Korma("8F4B0E"),
  Squirrel("8F8176"),
  Vista_Blue("8FD6B4"),
  Burgundy("900020"),
  Old_Brick("901E1E"),
  Hemp("907874"),
  Almond_Frost("907B71"),
  Sycamore("908D39"),
  Sangria("92000A"),
  Cumin("924321"),
  Beaver("926F5B"),
  Stonewall("928573"),
  Venus("928590"),
  Medium_Purple("9370DB"),
  Cornflower("93CCEA"),
  Algae_Green("93DFB8"),
  Copper_Rust("944747"),
  Arrowtown("948771"),
  Scarlett("950015"),
  Strikemaster("956387"),
  Mountain_Mist("959396"),
  Carmine("960018"),
  Brown("964B00"),
  Leather("967059"),
  Purple_Mountains_Majesty("9678B6"),
  Lavender_Purple("967BB6"),
  Pewter("96A8A1"),
  Summer_Green("96BBAB"),
  Au_Chico("97605D"),
  Wisteria("9771B5"),
  Atlantis("97CD2D"),
  Vin_Rouge("983D61"),
  Lilac_Bush("9874D3"),
  Bazaar("98777B"),
  Hacienda("98811B"),
  Pale_Oyster("988D77"),
  Mint_Green("98FF98"),
  Fresh_Eggplant("990066"),
  Violet_Eggplant("991199"),
  Tamarillo("991613"),
  Totem_Pole("991B07"),
  Copper_Rose("996666"),
  Amethyst("9966CC"),
  Mountbatten_Pink("997A8D"),
  Blue_Bell("9999CC"),
  Prairie_Sand("9A3820"),
  Toast("9A6E61"),
  Gurkha("9A9577"),
  Olivine("9AB973"),
  Shadow_Green("9AC2B8"),
  Oregon("9B4703"),
  Lemon_Grass("9B9E8F"),
  Stiletto("9C3336"),
  Hawaiian_Tan("9D5616"),
  Gull_Gray("9DACB7"),
  Pistachio("9DC209"),
  Granny_Smith_Apple("9DE093"),
  Anakiwa("9DE5FF"),
  Chelsea_Gem("9E5302"),
  Sepia_Skin("9E5B40"),
  Sage("9EA587"),
  Citron("9EA91F"),
  Rock_Blue("9EB1CD"),
  Morning_Glory("9EDEE0"),
  Cognac("9F381D"),
  Reef_Gold("9F821C"),
  Star_Dust("9F9F9C"),
  Santas_Gray("9FA0B1"),
  Sinbad("9FD7D3"),
  Feijoa("9FDD8C"),
  Tabasco("A02712"),
  Buttered_Rum("A1750D"),
  Hit_Gray("A1ADB5"),
  Citrus("A1C50A"),
  Aqua_Island("A1DAD7"),
  Water_Leaf("A1E9DE"),
  Flirt("A2006D"),
  Rouge("A23B6C"),
  Cape_Palliser("A26645"),
  Gray_Chateau("A2AAB3"),
  Edward("A2AEAB"),
  Pharlap("A3807B"),
  Amethyst_Smoke("A397B4"),
  Blizzard_Blue("A3E3ED"),
  Delta("A4A49D"),
  Wistful("A4A6D3"),
  Green_Smoke("A4AF6E"),
  Jazzberry_Jam("A50B5E"),
  Zorba("A59B91"),
  Bahia("A5CB0C"),
  Roof_Terracotta("A62F20"),
  Paarl("A65529"),
  Barley_Corn("A68B5B"),
  Donkey_Brown("A69279"),
  Dawn("A6A29A"),
  Mexican_Red("A72525"),
  Luxor_Gold("A7882C"),
  Rich_Gold("A85307"),
  Reno_Sand("A86515"),
  Coral_Tree("A86B6B"),
  Dusty_Gray("A8989B"),
  Dull_Lavender("A899E6"),
  Tallow("A8A589"),
  Bud("A8AE9C"),
  Locust("A8AF8E"),
  Norway("A8BD9F"),
  Chinook("A8E3BD"),
  Gray_Olive("A9A491"),
  Aluminium("A9ACB6"),
  Cadet_Blue("A9B2C3"),
  Schist("A9B497"),
  Tower_Gray("A9BDBF"),
  Perano("A9BEF2"),
  Opal("A9C6C2"),
  Night_Shadz("AA375A"),
  Fire("AA4203"),
  Muesli("AA8B5B"),
  Sandal("AA8D6F"),
  Shady_Lady("AAA5A9"),
  Logan("AAA9CD"),
  Spun_Pearl("AAABB7"),
  Regent_St_Blue("AAD6E6"),
  Magic_Mint("AAF0D1"),
  Lipstick("AB0563"),
  Royal_Heath("AB3472"),
  Sandrift("AB917A"),
  Cold_Purple("ABA0D9"),
  Bronco("ABA196"),
  Limed_Oak("AC8A56"),
  East_Side("AC91CE"),
  Lemon_Ginger("AC9E22"),
  Napa("ACA494"),
  Hillary("ACA586"),
  Cloudy("ACA59F"),
  Silver_Chalice("ACACAC"),
  Swamp_Green("ACB78E"),
  Spring_Rain("ACCBB1"),
  Conifer("ACDD4D"),
  Celadon("ACE1AF"),
  Mandalay("AD781B"),
  Casper("ADBED1"),
  Moss_Green("ADDFAD"),
  Padua("ADE6C4"),
  Green_Yellow("ADFF2F"),
  Hippie_Pink("AE4560"),
  Desert("AE6020"),
  Bouquet("AE809E"),
  Medium_Carmine("AF4035"),
  Apple_Blossom("AF4D43"),
  Brown_Rust("AF593E"),
  Driftwood("AF8751"),
  Alpine("AF8F2C"),
  Lucky("AF9F1C"),
  Martini("AFA09E"),
  Bombay("AFB1B8"),
  Pigeon_Post("AFBDD9"),
  Cadillac("B04C6A"),
  Matrix("B05D54"),
  Tapestry("B05E81"),
  Mai_Tai("B06608"),
  Del_Rio("B09A95"),
  Powder_Blue("B0E0E6"),
  Inch_Worm("B0E313"),
  Bright_Red("B10000"),
  Vesuvius("B14A0B"),
  Pumpkin_Skin("B1610B"),
  Santa_Fe("B16D52"),
  Teak("B19461"),
  Fringy_Flower("B1E2C1"),
  Ice_Cold("B1F4E7"),
  Shiraz("B20931"),
  Biloba_Flower("B2A1EA"),
  Tall_Poppy("B32D29"),
  Fiery_Orange("B35213"),
  Hot_Toddy("B38007"),
  Taupe_Gray("B3AF95"),
  La_Rioja("B3C110"),
  Well_Read("B43332"),
  Blush("B44668"),
  Jungle_Mist("B4CFD3"),
  Turkish_Rose("B57281"),
  Lavender("B57EDC"),
  Mongoose("B5A27F"),
  Olive_Green("B5B35C"),
  Jet_Stream("B5D2CE"),
  Cruise("B5ECDF"),
  Hibiscus("B6316C"),
  Thatch("B69D98"),
  Heathered_Gray("B6B095"),
  Eagle("B6BAA4"),
  Spindle("B6D1EA"),
  Gum_Leaf("B6D3BF"),
  Rust("B7410E"),
  Muddy_Waters("B78E5C"),
  Sahara("B7A214"),
  Husk("B7A458"),
  Nobel("B7B1B1"),
  Heather("B7C3D0"),
  Madang("B7F0BE"),
  Milano_Red("B81104"),
  Copper("B87333"),
  Gimblet("B8B56A"),
  Green_Spring("B8C1B1"),
  Celery("B8C25D"),
  Sail("B8E0F9"),
  Chestnut("B94E48"),
  Crail("B95140"),
  Marigold("B98D28"),
  Wild_Willow("B9C46A"),
  Rainee("B9C8AC"),
  Guardsman_Red("BA0101"),
  Rock_Spray("BA450C"),
  Bourbon("BA6F1E"),
  Pirate_Gold("BA7F03"),
  Nomad("BAB1A2"),
  Submarine("BAC7C9"),
  Charlotte("BAEEF9"),
  Medium_Red_Violet("BB3385"),
  Brandy_Rose("BB8983"),
  Rio_Grande("BBD009"),
  Surf("BBD7C1"),
  Powder_Ash("BCC9C2"),
  Tuscany("BD5E2E"),
  Quicksand("BD978E"),
  Silk("BDB1A8"),
  Malta("BDB2A1"),
  Chatelle("BDB3C7"),
  Lavender_Gray("BDBBD7"),
  French_Gray("BDBDC6"),
  Clay_Ash("BDC8B3"),
  Loblolly("BDC9CE"),
  French_Pass("BDEDFD"),
  London_Hue("BEA6C3"),
  Pink_Swan("BEB5B7"),
  Fuego("BEDE0D"),
  Rose_of_Sharon("BF5500"),
  Tide("BFB8B0"),
  Blue_Haze("BFBED8"),
  Silver_Sand("BFC1C2"),
  Key_Lime_Pie("BFC921"),
  Ziggurat("BFDBE2"),
  Lime("BFFF00"),
  Thunderbird("C02B18"),
  Mojo("C04737"),
  Old_Rose("C08081"),
  Silver("C0C0C0"),
  Pale_Leaf("C0D3B9"),
  Pixie_Green("C0D8B6"),
  Tia_Maria("C1440E"),
  Fuchsia_Pink("C154C1"),
  Buddha_Gold("C1A004"),
  Bison_Hide("C1B7A4"),
  Tea("C1BAB0"),
  Gray_Suit("C1BECD"),
  Sprout("C1D7B0"),
  Sulu("C1F07C"),
  Indochine("C26B03"),
  Twine("C2955D"),
  Cotton_Seed("C2BDB6"),
  Pumice("C2CAC4"),
  Jagged_Ice("C2E8E5"),
  Maroon_Flush("C32148"),
  Indian_Khaki("C3B091"),
  Pale_Slate("C3BFC1"),
  Gray_Nickel("C3C3BD"),
  Periwinkle_Gray("C3CDE6"),
  Tiara("C3D1D1"),
  Tropical_Blue("C3DDF9"),
  Cardinal("C41E3A"),
  Fuzzy_Wuzzy_Brown("C45655"),
  Orange_Roughy("C45719"),
  Mist_Gray("C4C4BC"),
  Coriander("C4D0B0"),
  Mint_Tulip("C4F4EB"),
  Mulberry("C54B8C"),
  Nugget("C59922"),
  Tussock("C5994B"),
  Sea_Mist("C5DBCA"),
  Yellow_Green("C5E17A"),
  Brick_Red("C62D42"),
  Contessa("C6726B"),
  Oriental_Pink("C69191"),
  Roti("C6A84B"),
  Ash("C6C3B5"),
  Kangaroo("C6C8BD"),
  Las_Palmas("C6E610"),
  Monza("C7031E"),
  Red_Violet("C71585"),
  Coral_Reef("C7BCA2"),
  Melrose("C7C1FF"),
  Cloud("C7C4BF"),
  Ghost("C7C9D5"),
  Pine_Glade("C7CD90"),
  Botticelli("C7DDE5"),
  Antique_Brass("C88A65"),
  Lilac("C8A2C8"),
  Hokey_Pokey("C8A528"),
  Lily("C8AABF"),
  Laser("C8B568"),
  Edgewater("C8E3D7"),
  Piper("C96323"),
  Pizza("C99415"),
  Light_Wisteria("C9A0DC"),
  Rodeo_Dust("C9B29B"),
  Sundance("C9B35B"),
  Earls_Green("C9B93B"),
  Silver_Rust("C9C0BB"),
  Conch("C9D9D2"),
  Reef("C9FFA2"),
  Aero_Blue("C9FFE5"),
  Flush_Mahogany("CA3435"),
  Turmeric("CABB48"),
  Paris_White("CADCD4"),
  Bitter_Lemon("CAE00D"),
  Skeptic("CAE6DA"),
  Viola("CB8FA9"),
  Foggy_Gray("CBCAB6"),
  Green_Mist("CBD3B0"),
  Nebula("CBDBD6"),
  Persian_Red("CC3333"),
  Burnt_Orange("CC5500"),
  Ochre("CC7722"),
  Puce("CC8899"),
  Thistle_Green("CCCAA8"),
  Periwinkle("CCCCFF"),
  Electric_Lime("CCFF00"),
  Tenn("CD5700"),
  Chestnut_Rose("CD5C5C"),
  Brandy_Punch("CD8429"),
  Onahau("CDF4FF"),
  Sorrell_Brown("CEB98F"),
  Cold_Turkey("CEBABA"),
  Yuma("CEC291"),
  Chino("CEC7A7"),
  Eunry("CFA39D"),
  Old_Gold("CFB53B"),
  Tasman("CFDCCF"),
  Surf_Crest("CFE5D2"),
  Humming_Bird("CFF9F3"),
  Scandal("CFFAF4"),
  Red_Stage("D05F04"),
  Hopbush("D06DA1"),
  Meteor("D07D12"),
  Perfume("D0BEF8"),
  Prelude("D0C0E5"),
  Tea_Green("D0F0C0"),
  Geebung("D18F1B"),
  Vanilla("D1BEA8"),
  Soft_Amber("D1C6B4"),
  Celeste("D1D2CA"),
  Mischka("D1D2DD"),
  Pear("D1E231"),
  Hot_Cinnamon("D2691E"),
  Raw_Sienna("D27D46"),
  Careys_Pink("D29EAA"),
  Tan("D2B48C"),
  Deco("D2DA97"),
  Blue_Romance("D2F6DE"),
  Gossip("D2F8B0"),
  Sisal("D3CBBA"),
  Swirl("D3CDC5"),
  Charm("D47494"),
  Clam_Shell("D4B6AF"),
  Straw("D4BF8D"),
  Akaroa("D4C4A8"),
  Bird_Flower("D4CD16"),
  Iron("D4D7D9"),
  Geyser("D4DFE2"),
  Hawkes_Blue("D4E2FC"),
  Grenadier("D54600"),
  Can_Can("D591A4"),
  Whiskey("D59A6F"),
  Winter_Hazel("D5D195"),
  Granny_Apple("D5F6E3"),
  My_Pink("D69188"),
  Tacha("D6C562"),
  Moon_Raker("D6CEF6"),
  Quill_Gray("D6D6D1"),
  Snowy_Mint("D6FFDB"),
  New_York_Pink("D7837F"),
  Pavlova("D7C498"),
  Fog("D7D0FF"),
  Valencia("D84437"),
  Japonica("D87C63"),
  Thistle("D8BFD8"),
  Maverick("D8C2D5"),
  Foam("D8FCFA"),
  Cabaret("D94972"),
  Burning_Sand("D99376"),
  Cameo("D9B99B"),
  Timberwolf("D9D6CF"),
  Tana("D9DCC1"),
  Link_Water("D9E4F5"),
  Mabel("D9F7FF"),
  Cerise("DA3287"),
  Flame_Pea("DA5B38"),
  Bamboo("DA6304"),
  Red_Damask("DA6A41"),
  Orchid("DA70D6"),
  Copperfield("DA8A67"),
  Golden_Grass("DAA520"),
  Zanah("DAECD6"),
  Iceberg("DAF4F0"),
  Oyster_Bay("DAFAFF"),
  Cranberry("DB5079"),
  Petite_Orchid("DB9690"),
  Di_Serria("DB995E"),
  Alto("DBDBDB"),
  Frosted_Mint("DBFFF8"),
  Crimson("DC143C"),
  Punch("DC4333"),
  Galliano("DCB20C"),
  Blossom("DCB4BC"),
  Wattle("DCD747"),
  Westar("DCD9D2"),
  Moon_Mist("DCDDCC"),
  Caper("DCEDB4"),
  Swans_Down("DCF0EA"),
  Swiss_Coffee("DDD6D5"),
  White_Ice("DDF9F1"),
  Cerise_Red("DE3163"),
  Roman("DE6360"),
  Tumbleweed("DEA681"),
  Gold_Tips("DEBA13"),
  Brandy("DEC196"),
  Wafer("DECBC6"),
  Sapling("DED4A4"),
  Barberry("DED717"),
  Beryl_Green("DEE5C0"),
  Pattens_Blue("DEF5FF"),
  Heliotrope("DF73FF"),
  Apache("DFBE6F"),
  Chenin("DFCD6F"),
  Lola("DFCFDB"),
  Willow_Brook("DFECDA"),
  Chartreuse_Yellow("DFFF00"),
  Mauve("E0B0FF"),
  Anzac("E0B646"),
  Harvest_Gold("E0B974"),
  Calico("E0C095"),
  Baby_Blue("E0FFFF"),
  Sunglo("E16865"),
  Equator("E1BC64"),
  Pink_Flare("E1C0C8"),
  Periglacial_Blue("E1E6D6"),
  Kidnapper("E1EAD4"),
  Tara("E1F6E8"),
  Mandy("E25465"),
  Terracotta("E2725B"),
  Golden_Bell("E28913"),
  Shocking("E292C0"),
  Dixie("E29418"),
  Light_Orchid("E29CD2"),
  Snuff("E2D8ED"),
  Mystic("E2EBED"),
  Apple_Green("E2F3EC"),
  Razzmatazz("E30B5C"),
  Alizarin_Crimson("E32636"),
  Cinnabar("E34234"),
  Cavern_Pink("E3BEBE"),
  Peppermint("E3F5E1"),
  Mindaro("E3F988"),
  Deep_Blush("E47698"),
  Gamboge("E49B0F"),
  Melanie("E4C2D5"),
  Twilight("E4CFDE"),
  Bone("E4D1C0"),
  Sunflower("E4D422"),
  Grain_Brown("E4D5B7"),
  Zombie("E4D69B"),
  Frostee("E4F6E7"),
  Snow_Flurry("E4FFD1"),
  Amaranth("E52B50"),
  Zest("E5841B"),
  Dust_Storm("E5CCC9"),
  Stark_White("E5D7BD"),
  Hampton("E5D8AF"),
  Bon_Jour("E5E0E1"),
  Mercury("E5E5E5"),
  Polar("E5F9F6"),
  Trinidad("E64E03"),
  Gold_Sand("E6BE8A"),
  Cashmere("E6BEA5"),
  Double_Spanish_White("E6D7B9"),
  Satin_Linen("E6E4D4"),
  Harp("E6F2EA"),
  Off_Green("E6F8F3"),
  Hint_of_Green("E6FFE9"),
  Tranquil("E6FFFF"),
  Mango_Tango("E77200"),
  Christine("E7730A"),
  Tonys_Pink("E79F8C"),
  Kobi("E79FC4"),
  Rose_Fog("E7BCB4"),
  Corn("E7BF05"),
  Putty("E7CD8C"),
  Gray_Nurse("E7ECE6"),
  Lily_White("E7F8FF"),
  Bubbles("E7FEFF"),
  Fire_Bush("E89928"),
  Shilo("E8B9B3"),
  Pearl_Bush("E8E0D5"),
  Green_White("E8EBE0"),
  Chrome_White("E8F1D4"),
  Gin("E8F2EB"),
  Aqua_Squeeze("E8F5F2"),
  Clementine("E96E00"),
  Burnt_Sienna("E97451"),
  Tahiti_Gold("E97C07"),
  Oyster_Pink("E9CECD"),
  Confetti("E9D75A"),
  Ebb("E9E3E3"),
  Ottoman("E9F8ED"),
  Clear_Day("E9FFFD"),
  Carissma("EA88A8"),
  Porsche("EAAE69"),
  Tulip_Tree("EAB33B"),
  Rob_Roy("EAC674"),
  Raffia("EADAB8"),
  White_Rock("EAE8D4"),
  Panache("EAF6EE"),
  Solitude("EAF6FF"),
  Aqua_Spring("EAF9F5"),
  Dew("EAFFFE"),
  Apricot("EB9373"),
  Zinnwaldite("EBC2AF"),
  Fuel_Yellow("ECA927"),
  Ronchi("ECC54E"),
  French_Lilac("ECC7EE"),
  Just_Right("ECCDB9"),
  Wild_Rice("ECE090"),
  Fall_Green("ECEBBD"),
  Aths_Special("ECEBCE"),
  Starship("ECF245"),
  Red_Ribbon("ED0A3F"),
  Tango("ED7A1C"),
  Carrot_Orange("ED9121"),
  Sea_Pink("ED989E"),
  Tacao("EDB381"),
  Desert_Sand("EDC9AF"),
  Pancho("EDCDAB"),
  Chamois("EDDCB1"),
  Primrose("EDEA99"),
  Frost("EDF5DD"),
  Aqua_Haze("EDF5F5"),
  Zumthor("EDF6FF"),
  Narvik("EDF9F1"),
  Honeysuckle("EDFC84"),
  Lavender_Magenta("EE82EE"),
  Beauty_Bush("EEC1BE"),
  Chalky("EED794"),
  Almond("EED9C4"),
  Flax("EEDC82"),
  Bizarre("EEDEDA"),
  Double_Colonial_White("EEE3AD"),
  Cararra("EEEEE8"),
  Manz("EEEF78"),
  Tahuna_Sands("EEF0C8"),
  Athens_Gray("EEF0F3"),
  Tusk("EEF3C3"),
  Loafer("EEF4DE"),
  Catskill_White("EEF6F7"),
  Twilight_Blue("EEFDFF"),
  Jonquil("EEFF9A"),
  Rice_Flower("EEFFE2"),
  Jaffa("EF863F"),
  Gallery("EFEFEF"),
  Porcelain("EFF2F3"),
  Mauvelous("F091A9"),
  Golden_Dream("F0D52D"),
  Golden_Sand("F0DB7D"),
  Buff("F0DC82"),
  Prim("F0E2EC"),
  Khaki("F0E68C"),
  Selago("F0EEFD"),
  Titan_White("F0EEFF"),
  Alice_Blue("F0F8FF"),
  Feta("F0FCEA"),
  Gold_Drop("F18200"),
  Wewak("F19BAB"),
  Sahara_Sand("F1E788"),
  Parchment("F1E9D2"),
  Blue_Chalk("F1E9FF"),
  Mint_Julep("F1EEC1"),
  Seashell("F1F1F1"),
  Saltpan("F1F7F2"),
  Tidal("F1FFAD"),
  Chiffon("F1FFC8"),
  Flamingo("F2552A"),
  Tangerine("F28500"),
  Mandys_Pink("F2C3B2"),
  Concrete("F2F2F2"),
  Black_Squeeze("F2FAFA"),
  Pomegranate("F34723"),
  Buttercup("F3AD16"),
  New_Orleans("F3D69D"),
  Vanilla_Ice("F3D9DF"),
  Sidecar("F3E7BB"),
  Dawn_Pink("F3E9E5"),
  Wheatfield("F3EDCF"),
  Canary("F3FB62"),
  Orinoco("F3FBD4"),
  Carla("F3FFD8"),
  Hollywood_Cerise("F400A1"),
  Sandy_brown("F4A460"),
  Saffron("F4C430"),
  Ripe_Lemon("F4D81C"),
  Janna("F4EBD3"),
  Pampas("F4F2EE"),
  Wild_Sand("F4F4F4"),
  Zircon("F4F8FF"),
  Froly("F57584"),
  Cream_Can("F5C85C"),
  Manhattan("F5C999"),
  Maize("F5D5A0"),
  Wheat("F5DEB3"),
  Sandwisp("F5E7A2"),
  Pot_Pourri("F5E7E2"),
  Albescent_White("F5E9D3"),
  Soft_Peach("F5EDEF"),
  Ecru_White("F5F3E5"),
  Beige("F5F5DC"),
  Golden_Fizz("F5FB3D"),
  Australian_Mint("F5FFBE"),
  French_Rose("F64A8A"),
  Brilliant_Rose("F653A6"),
  Illusion("F6A4C9"),
  Merino("F6F0E6"),
  Black_Haze("F6F7F7"),
  Spring_Sun("F6FFDC"),
  Violet_Red("F7468A"),
  Chilean_Fire("F77703"),
  Persian_Pink("F77FBE"),
  Rajah("F7B668"),
  Azalea("F7C8DA"),
  We_Peep("F7DBE6"),
  Quarter_Spanish_White("F7F2E1"),
  Whisper("F7F5FA"),
  Snow_Drift("F7FAF7"),
  Casablanca("F8B853"),
  Chantilly("F8C3DF"),
  Cherub("F8D9E9"),
  Marzipan("F8DB9D"),
  Energy_Yellow("F8DD5C"),
  Givry("F8E4BF"),
  White_Linen("F8F0E8"),
  Magnolia("F8F4FF"),
  Spring_Wood("F8F6F1"),
  Coconut_Cream("F8F7DC"),
  White_Lilac("F8F7FC"),
  Desert_Storm("F8F8F7"),
  Texas("F8F99C"),
  Corn_Field("F8FACD"),
  Mimosa("F8FDD3"),
  Carnation("F95A61"),
  Saffron_Mango("F9BF58"),
  Carousel_Pink("F9E0ED"),
  Dairy_Cream("F9E4BC"),
  Portica("F9E663"),
  Amour("F9EAF3"),
  Rum_Swizzle("F9F8E4"),
  Dolly("F9FF8B"),
  Sugar_Cane("F9FFF6"),
  Ecstasy("FA7814"),
  Tan_Hide("FA9D5A"),
  Corvette("FAD3A2"),
  Peach_Yellow("FADFAD"),
  Turbo("FAE600"),
  Astra("FAEAB9"),
  Champagne("FAECCC"),
  Linen("FAF0E6"),
  Fantasy("FAF3F0"),
  Citrine_White("FAF7D6"),
  Alabaster("FAFAFA"),
  Hint_of_Yellow("FAFDE4"),
  Milan("FAFFA4"),
  Brink_Pink("FB607F"),
  Geraldine("FB8989"),
  Lavender_Rose("FBA0E3"),
  Sea_Buckthorn("FBA129"),
  Sun("FBAC13"),
  Lavender_Pink("FBAED2"),
  Rose_Bud("FBB2A3"),
  Cupid("FBBEDA"),
  Classic_Rose("FBCCE7"),
  Apricot_Peach("FBCEB1"),
  Banana_Mania("FBE7B2"),
  Marigold_Yellow("FBE870"),
  Festival("FBE96C"),
  Sweet_Corn("FBEA8C"),
  Candy_Corn("FBEC5D"),
  Hint_of_Red("FBF9F9"),
  Shalimar("FBFFBA"),
  Shocking_Pink("FC0FC0"),
  Tickle_Me_Pink("FC80A5"),
  Tree_Poppy("FC9C1D"),
  Lightning_Yellow("FCC01E"),
  Goldenrod("FCD667"),
  Candlelight("FCD917"),
  Cherokee("FCDA98"),
  Double_Pearl_Lusta("FCF4D0"),
  Pearl_Lusta("FCF4DC"),
  Vista_White("FCF8F7"),
  Bianca("FCFBF3"),
  Moon_Glow("FCFEDA"),
  China_Ivory("FCFFE7"),
  Ceramic("FCFFF9"),
  Torch_Red("FD0E35"),
  Wild_Watermelon("FD5B78"),
  Crusta("FD7B33"),
  Sorbus("FD7C07"),
  Sweet_Pink("FD9FA2"),
  Light_Apricot("FDD5B1"),
  Pig_Pink("FDD7E4"),
  Cinderella("FDE1DC"),
  Golden_Glow("FDE295"),
  Lemon("FDE910"),
  Old_Lace("FDF5E6"),
  Half_Colonial_White("FDF6D3"),
  Drover("FDF7AD"),
  Pale_Prim("FDFEB8"),
  Cumulus("FDFFD5"),
  Persian_Rose("FE28A2"),
  Sunset_Orange("FE4C40"),
  Bittersweet("FE6F5E"),
  California("FE9D04"),
  Yellow_Sea("FEA904"),
  Melon("FEBAAD"),
  Bright_Sun("FED33C"),
  Dandelion("FED85D"),
  Salomie("FEDB8D"),
  Cape_Honey("FEE5AC"),
  Remy("FEEBF3"),
  Oasis("FEEFCE"),
  Bridesmaid("FEF0EC"),
  Beeswax("FEF2C7"),
  Bleach_White("FEF3D8"),
  Pipi("FEF4CC"),
  Half_Spanish_White("FEF4DB"),
  Wisp_Pink("FEF4F8"),
  Provincial_Pink("FEF5F1"),
  Half_Dutch_White("FEF7DE"),
  Solitaire("FEF8E2"),
  White_Pointer("FEF8FF"),
  Off_Yellow("FEF9E3"),
  Orange_White("FEFCED"),
  Red("FF0000"),
  Rose("FF007F"),
  Purple_Pizzazz("FF00CC"),
  Magenta("FF00FF"),
  Scarlet("FF2400"),
  Wild_Strawberry("FF3399"),
  Razzle_Dazzle_Rose("FF33CC"),
  Radical_Red("FF355E"),
  Red_Orange("FF3F34"),
  Coral_Red("FF4040"),
  Vermilion("FF4D00"),
  International_Orange("FF4F00"),
  Outrageous_Orange("FF6037"),
  Blaze_Orange("FF6600"),
  Pink_Flamingo("FF66FF"),
  Orange("FF681F"),
  Hot_Pink("FF69B4"),
  Persimmon("FF6B53"),
  Blush_Pink("FF6FFF"),
  Burning_Orange("FF7034"),
  Pumpkin("FF7518"),
  Flamenco("FF7D07"),
  Flush_Orange("FF7F00"),
  Coral("FF7F50"),
  Salmon("FF8C69"),
  Pizazz("FF9000"),
  West_Side("FF910F"),
  Pink_Salmon("FF91A4"),
  Neon_Carrot("FF9933"),
  Atomic_Tangerine("FF9966"),
  Vivid_Tangerine("FF9980"),
  Sunshade("FF9E2C"),
  Orange_Peel("FFA000"),
  Mona_Lisa("FFA194"),
  Web_Orange("FFA500"),
  Carnation_Pink("FFA6C9"),
  Hit_Pink("FFAB81"),
  Yellow_Orange("FFAE42"),
  Cornflower_Lilac("FFB0AC"),
  Sundown("FFB1B3"),
  My_Sin("FFB31F"),
  Texas_Rose("FFB555"),
  Cotton_Candy("FFB7D5"),
  Macaroni_and_Cheese("FFB97B"),
  Selective_Yellow("FFBA00"),
  Koromiko("FFBD5F"),
  Amber("FFBF00"),
  Wax_Flower("FFC0A8"),
  Pink("FFC0CB"),
  Your_Pink("FFC3C0"),
  Supernova("FFC901"),
  Flesh("FFCBA4"),
  Sunglow("FFCC33"),
  Golden_Tainoi("FFCC5C"),
  Peach_Orange("FFCC99"),
  Chardonnay("FFCD8C"),
  Pastel_Pink("FFD1DC"),
  Romantic("FFD2B7"),
  Grandis("FFD38C"),
  Gold("FFD700"),
  School_bus_Yellow("FFD800"),
  Cosmos("FFD8D9"),
  Mustard("FFDB58"),
  Peach_Schnapps("FFDCD6"),
  Caramel("FFDDAF"),
  Tuft_Bush("FFDDCD"),
  Watusi("FFDDCF"),
  Pink_Lace("FFDDF4"),
  Navajo_White("FFDEAD"),
  Frangipani("FFDEB3"),
  Pippin("FFE1DF"),
  Pale_Rose("FFE1F2"),
  Negroni("FFE2C5"),
  Cream_Brulee("FFE5A0"),
  Peach("FFE5B4"),
  Tequila("FFE6C7"),
  Kournikova("FFE772"),
  Sandy_Beach("FFEAC8"),
  Karry("FFEAD4"),
  Broom("FFEC13"),
  Colonial_White("FFEDBC"),
  Derby("FFEED8"),
  Vis_Vis("FFEFA1"),
  Egg_White("FFEFC1"),
  Papaya_Whip("FFEFD5"),
  Fair_Pink("FFEFEC"),
  Peach_Cream("FFF0DB"),
  Lavender_blush("FFF0F5"),
  Gorse("FFF14F"),
  Buttermilk("FFF1B5"),
  Pink_Lady("FFF1D8"),
  Forget_Me_Not("FFF1EE"),
  Tutu("FFF1F9"),
  Picasso("FFF39D"),
  Chardon("FFF3F1"),
  Paris_Daisy("FFF46E"),
  Barley_White("FFF4CE"),
  Egg_Sour("FFF4DD"),
  Sazerac("FFF4E0"),
  Serenade("FFF4E8"),
  Chablis("FFF4F3"),
  Seashell_Peach("FFF5EE"),
  Sauvignon("FFF5F3"),
  Milk_Punch("FFF6D4"),
  Varden("FFF6DF"),
  Rose_White("FFF6F5"),
  Baja_White("FFF8D1"),
  Gin_Fizz("FFF9E2"),
  Early_Dawn("FFF9E6"),
  Lemon_Chiffon("FFFACD"),
  Bridal_Heath("FFFAF4"),
  Scotch_Mist("FFFBDC"),
  Soapstone("FFFBF9"),
  Witch_Haze("FFFC99"),
  Buttery_White("FFFCEA"),
  Island_Spice("FFFCEE"),
  Cream("FFFDD0"),
  Chilean_Heath("FFFDE6"),
  Travertine("FFFDE8"),
  Orchid_White("FFFDF3"),
  Quarter_Pearl_Lusta("FFFDF4"),
  Half_and_Half("FFFEE1"),
  Apricot_White("FFFEEC"),
  Rice_Cake("FFFEF0"),
  Black_White("FFFEF6"),
  Romance("FFFEFD"),
  Yellow("FFFF00"),
  Laser_Lemon("FFFF66"),
  Pale_Canary("FFFF99"),
  Portafino("FFFFB4"),
  Ivory("FFFFF0"),
  White("FFFFFF");

  /**
   * the values.
   */
  private static final Colors[] VALUES = Colors.values();

  /**
   * the hex code.
   */
  @NotNull
  @Getter
  private final String hexCode;

  /**
   * the to string.
   */
  @NotNull
  @Getter
  private final String toString;

  /**
   * ctor.
   *
   * @param hexCode the hex code.
   */
  Colors(@NotNull final String hexCode) {
    this.hexCode = hexCode.toLowerCase(Locale.ROOT);
    this.toString = this.name().toLowerCase(Locale.ROOT).replace("_", "");
  }

  /**
   * registers the default custom colors.
   */
  public static void registerAll() {
    for (final var value : Colors.VALUES) {
      Color.addColor(value.toString(), value.hexCode());
    }
  }
}
