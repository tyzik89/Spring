beans {
    random(Random)

    xmlns([ctx:'http://www.springframework.org/schema/context'])
    ctx.'component-scan'('base-package':"work.vladimirs")
}
