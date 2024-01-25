
# Projekt alfa 3 -- Datbazovy system
## Kuta Samuel C4b 

**Uzivatelske rozrhani pro praci s databazi v oblasti.**

## Table of contents (TOC)
1.
2.
3.
4.
5.


### Potřeby pro spuštění
Některé věci nejsou striktně potřeba ke spuštění, ale pro plnou funkcionalitu a nejlepší zkušenost je nejlepší udělat vše. Tyto kroky jsou označeny jako *NEPOVINNÉ*
- - *Specificky je idealní pokud je nainstalovaný Gradle pro případné problémy a přístup k javadoc dokumentaci, a jiným užitečným částem.*
- **Java verze 20.0 +**
    - **Dá se zjistit pomocí konzolového příkazu `java --version`**
- **Gradle verze 8.4 +** - *NEPOVINNÉ*

### Jak spustit program

Prosím nejdříve zkontrolujte konfiguraci jestli jsou buď veškerá nastavení na `default`, a nebo jestli vyhovuje vaším představám.

- **Pokud je nainstalován Gradle** *NEPOVINNÉ*
    - **Linux / Unix / Mac**
        - Navigujte do adresáře programu v konzoli
    použijte tento příkaz : `./gradlew build`
    - **Windows**
        - **Pokud je nainstalován Gradle**
            - Navigujte do adresáře programu v konzoli
        použijte tento příkaz : `gradlew build`

- **Navigujte** do adresáře kde kde máte projekt uložený, a spusťte soubor **Kuta_Alfa2_RunMe-all.jar**
    - Můžete spustit příkazem `java -jar Kuta_Alfa2_RunMe-all.jar`

### Konfigurace programu
Veškerou konfiguraci se dá provádět úpravami souboru config.json v adresáři config.

Config soubor je ve formátu json, a pokud bude dodržen přesný formát, může být nahrazen vlastním config souborem.

Příklad **config** souboru:


    {
    "cesta_k_souboru":"/home//Projects/alfa2/src/main/resources/testTextLong.txt",
    "adresar_umisteni_outputu":"default",
    "nazev_output_souboru":"default",
    "casovy_tag_v_nazvu":"A",
    "adresar_umisteni_error_logu":"default",
    "adresar_umisteni_operacniho_logu":"default"
    }

**Defaultní konfigurace**
- Pro defaultní konfiguraci, například pro testování, by měly být všechny hodnoty zapsané na `default`
- **Defaultní konfigurace**:
    
        {
        "cesta_k_souboru":"default",
        "adresar_umisteni_outputu":"default",
        "nazev_output_souboru":"default",
        "casovy_tag_v_nazvu":"default",
        "adresar_umisteni_error_logu":"default",
        "adresar_umisteni_operacniho_logu":"default"
        }
    


**Dá se konfigurovat**:
- **Input soubor**
    - Který soubor bude přečten a text v něm kompresován
    - Cesta k souboru
- **Adresář output souboru**
    - Ve kterém adresáři bude soubor s kompresovaným textem uložen
    - Cesta do adresáře
- **Jméno output souboru**
    - Jak se má jmenovat soubor s kompresovaným textem
- **Časový tag k output souboru**
    - Jestli se do jména kompresovaného souboru má připsat čas, kdy se operace stala
    - A = ano | N = ne
- **Adresář error logu**
    - Ve kterém adresáři bude uložen záznam o chybách
- **Adresář operacního logu**
    - Ve kterém adresáři bude uložen záznam o operacích
### Dokumentace
- **Programátorská dokumentace** 
    - Po provedení Gradle příkazu v části [Jak spustit program](#jak-spustit-program) bude ve adresáři build/docs/javadoc index.html soubor. Zobrazte v prohlížeči pro kompletní javadoc.
    - Zdrojový kód je také dokumentován
- **Uživatelská dokumentace**
    - Teď ji čtete, tento soubor slouží jako uživatelská dokumentace

### Testování
- Veškeré unit testy naleznete v adresáři **src/test/java/com/kuta**
- Unit testy můžete spustit příkazem pokud je nainstalovaný Gradle: NEPOVINNÉ
    - **Linux** `./gradlew test`
    - **Windows** `gradlew test`
    - Zprávu o provedených testech naleznete v adresáři **build/reports/tests/test/index.html** otevřete soubor v prohlížeči


## Závislosti / Dependencies
- Program využívá nástroje na JSON Serializace a Deserializace od Googlu. Nástroj se jmenuje [GSON](https://github.com/google/gson) 

- Na unit testování program využíva **JUnit**, protože Java nemá ve svých základních knihovnách způsob jak unit testovat.
- **JUnit** je best practice v unit testování

## Závěr a nedostatky
- Zadání technicky splňuje do nějáké míry všechny požadavky, ale má své nedostatky.
- Specificky **Komprese/Komprimace** byla udělaná velice rychle a nesofistikovaně, s průměrným zmenšením cca **10%**
- Unit testy testují hlavní funkce programu, nepokrývají celý program.
