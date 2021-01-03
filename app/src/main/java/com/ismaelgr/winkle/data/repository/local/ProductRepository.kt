package com.ismaelgr.winkle.data.repository.local

import com.ismaelgr.winkle.data.entity.Categorias
import com.ismaelgr.winkle.data.entity.Producto
import com.ismaelgr.winkle.data.repository.needs.ProductRepositoryNeed
import io.reactivex.rxjava3.core.Maybe

class ProductRepository : ProductRepositoryNeed {

    private val products = listOf(
        Producto(
            "1",
            "Portatil MSI i7 16RAM RTX 2070",
            "*Este equipo NO cuenta con sistema operativo instalado. ¿Quieres adquirir un sistema operativo de forma separada?\n" +
                    "\n" +
                    "Portátil MSI Modern 14 B10MW 026XES con pantalla Full HD de 14\" y un procesador excelente para ultrabooks Intel® Core™ i7-10510U con un consumo bajo de solamente 15 W. \n" +
                    "\n" +
                    "Rendimiento y entretenimiento con la 10ª generación \n" +
                    "Con el procesador Intel® Core™ i7-10510U de 4 núcleos tendrás la potencia suficiente para realizar todas las tareas de forma ágil y sin fallos. Diseñado para ser tan inteligente como sus usuarios, proporcionar experiencias de entretenimiento impresionantes y ofrecer una conectividad ultrarrápida, tendrás experiencias de entretenimiento increíblemente inmersivo, que antes eran muy difíciles de conseguir en dispositivos tan finos. La velocidad de 1.80 GHz, aunque puede llegar a los 4.90 GHz en modo turbo con Intel® Turbo Boost, te va a permitir realizar aquellas tareas que requieren más recursos de una manera ligera y la tecnología Intel® Hyper-Threading te permitirá realizar más trabajo en paralelo en las aplicaciones con muchos subprocesos, completando antes las tareas. Además, está equipado con 16 GB de RAM DDR4 de 2666 MHz, con lo que no tendrás problemas tanto si quieres trabajar en multitarea como correr procesos que requieran mucho espacio.\n" +
                    "\n" +
                    "Excelente autonomía de batería de 3 celdas\n" +
                    "Gracias a su procesador y tarjeta gráfica integrada, el consumo de batería se reduce considerablemente siendo ideal para ultrabooks. Con una batería de 52 Whr tendrás gran autonomía para todo el día.\n" +
                    "\n" +
                    "¿Tiene ventajas no tener sistema operativo en tu equipo? \n" +
                    "Los equipos FreeDos (sin sistema operativo) como el de este ordenador te dan la libertad de funcionar con el sistema operativo que te vaya mejor para trabajar. Si no te convence un sistema concreto o necesitas algo muy específico que no te pueden ofrecer los sistemas convencionales, los ordenadores FreeDos son la mejor opción para ello.",
            "https://assets.mmsrg.com/isr/166325/c1/-/ASSET_MMS_78225297/fee_786_587_png/Port%C3%A1til---MSI-Modern-14--14-%22-Full-HD--Intel%C2%AE-Core%E2%84%A2-i7-10510U--16-GB--512-GB-SSD--UHD-Graphics--FreeDOS",
            listOf(
                "https://assets.mmsrg.com/isr/166325/c1/-/ASSET_MMS_78225294/fee_52_41_png/Port%C3%A1til---MSI-Modern-14--14-%22-Full-HD--Intel%C2%AE-Core%E2%84%A2-i7-10510U--16-GB--512-GB-SSD--UHD-Graphics--FreeDOS",
                "https://assets.mmsrg.com/isr/166325/c1/-/ASSET_MMS_78225295/fee_52_41_png/Port%C3%A1til---MSI-Modern-14--14-%22-Full-HD--Intel%C2%AE-Core%E2%84%A2-i7-10510U--16-GB--512-GB-SSD--UHD-Graphics--FreeDOS",
                "https://assets.mmsrg.com/isr/166325/c1/-/ASSET_MMS_78225296/fee_52_41_png/Port%C3%A1til---MSI-Modern-14--14-%22-Full-HD--Intel%C2%AE-Core%E2%84%A2-i7-10510U--16-GB--512-GB-SSD--UHD-Graphics--FreeDOS",
                "https://images-na.ssl-images-amazon.com/images/I/71h6PpGaz9L._AC_SL1500_.jpg",
                "https://www.worten.es/i/f27fa9006a15d9bd4cc281f97681b14585bc4216.jpg",
                "https://www.worten.es/i/f27fa9006a15d9bd4cc281f97681b14585bc4216.jpg",
                "https://www.worten.es/i/f27fa9006a15d9bd4cc281f97681b14585bc4216.jpg",
                "https://www.worten.es/i/f27fa9006a15d9bd4cc281f97681b14585bc4216.jpg",
                "https://www.worten.es/i/f27fa9006a15d9bd4cc281f97681b14585bc4216.jpg",
                "https://www.worten.es/i/f27fa9006a15d9bd4cc281f97681b14585bc4216.jpg"
            ),
            1400F,
            "1",
            true,
            listOf("pc", "portatil", "gaming", "hp"),
            Categorias.TECNOLOGIA.ordinal
        ),
        Producto(
            "2",
            "Peine bien fachero",
            "Esta nuevo señores lo digo enserio...",
            "https://ae01.alicdn.com/kf/H20ac92cf50e4496ea86209e348d9285aN/Peine-de-dientes-finos-1-unids-lote-peine-de-pl-stico-para-desenredar-peine-de-cola.jpg_q50.jpg",
            listOf(
                "https://www.worten.es/i/f27fa9006a15d9bd4cc281f97681b14585bc4216.jpg",
                "https://www.worten.es/i/f27fa9006a15d9bd4cc281f97681b14585bc4216.jpg",
                "https://www.worten.es/i/f27fa9006a15d9bd4cc281f97681b14585bc4216.jpg",
                "https://www.worten.es/i/f27fa9006a15d9bd4cc281f97681b14585bc4216.jpg",
                "https://www.worten.es/i/f27fa9006a15d9bd4cc281f97681b14585bc4216.jpg",
                "https://www.worten.es/i/f27fa9006a15d9bd4cc281f97681b14585bc4216.jpg"
            ),
            12.21F,
            "2",
            true,
            listOf("peine", "accesorios"),
            Categorias.ACCESORIOS.ordinal
        ),
        Producto(
            "3",
            "Apuntes de clase de informatica",
            "De nada xd",
            "https://www.mentesliberadas.com/wp-content/uploads/2018/10/apuntes-bonitos-principal-1024x567.jpg",
            listOf(
                "https://www.worten.es/i/f27fa9006a15d9bd4cc281f97681b14585bc4216.jpg",
                "https://www.worten.es/i/f27fa9006a15d9bd4cc281f97681b14585bc4216.jpg",
                "https://www.worten.es/i/f27fa9006a15d9bd4cc281f97681b14585bc4216.jpg",
                "https://www.worten.es/i/f27fa9006a15d9bd4cc281f97681b14585bc4216.jpg",
                "https://www.worten.es/i/f27fa9006a15d9bd4cc281f97681b14585bc4216.jpg",
                "https://www.worten.es/i/f27fa9006a15d9bd4cc281f97681b14585bc4216.jpg"
            ),
            10F,
            "1",
            true,
            listOf("universidad de sevilla", "ingenieria informatica"),
            Categorias.ESTUDIOS.ordinal
        ),
        Producto(
            "4",
            "Ajedrez seminuevo",
            "Como juegue una mas con mi hijo lo estampo xd",
            "https://upload.wikimedia.org/wikipedia/commons/thumb/f/f3/Staunton_chess_set.jpg/300px-Staunton_chess_set.jpg",
            listOf(
                "https://www.worten.es/i/f27fa9006a15d9bd4cc281f97681b14585bc4216.jpg",
                "https://www.worten.es/i/f27fa9006a15d9bd4cc281f97681b14585bc4216.jpg",
                "https://www.worten.es/i/f27fa9006a15d9bd4cc281f97681b14585bc4216.jpg",
                "https://www.worten.es/i/f27fa9006a15d9bd4cc281f97681b14585bc4216.jpg",
                "https://www.worten.es/i/f27fa9006a15d9bd4cc281f97681b14585bc4216.jpg",
                "https://www.worten.es/i/f27fa9006a15d9bd4cc281f97681b14585bc4216.jpg"
            ),
            4.5F,
            "2",
            true,
            listOf("juegos de mesa", "ajedrez"),
            Categorias.JUEGOS.ordinal
        ),
        Producto(
            "5",
            "Pixel 5 de alemania",
            "Esta nuevo señores lo digo enserio...",
            "https://cdn.mos.cms.futurecdn.net/CmPPvHfRRCsTCHXAdy9Hka.jpg",
            listOf(
                "https://www.worten.es/i/f27fa9006a15d9bd4cc281f97681b14585bc4216.jpg",
                "https://www.worten.es/i/f27fa9006a15d9bd4cc281f97681b14585bc4216.jpg",
                "https://www.worten.es/i/f27fa9006a15d9bd4cc281f97681b14585bc4216.jpg",
                "https://www.worten.es/i/f27fa9006a15d9bd4cc281f97681b14585bc4216.jpg",
                "https://www.worten.es/i/f27fa9006a15d9bd4cc281f97681b14585bc4216.jpg",
                "https://www.worten.es/i/f27fa9006a15d9bd4cc281f97681b14585bc4216.jpg"
            ),
            650F,
            "2",
            true,
            listOf("pixel 5", "pixel", "movil", "telefono"),
            Categorias.TECNOLOGIA.ordinal
        ),
        Producto(
            "6",
            "Cenicero de mi hijo",
            "Esta nuevo señores lo digo enserio...",
            "https://www.suitevintage.cat/wp-content/uploads/2018/02/cenicero-agata.jpg",
            listOf(
                "https://www.worten.es/i/f27fa9006a15d9bd4cc281f97681b14585bc4216.jpg",
                "https://www.worten.es/i/f27fa9006a15d9bd4cc281f97681b14585bc4216.jpg",
                "https://www.worten.es/i/f27fa9006a15d9bd4cc281f97681b14585bc4216.jpg",
                "https://www.worten.es/i/f27fa9006a15d9bd4cc281f97681b14585bc4216.jpg",
                "https://www.worten.es/i/f27fa9006a15d9bd4cc281f97681b14585bc4216.jpg",
                "https://www.worten.es/i/f27fa9006a15d9bd4cc281f97681b14585bc4216.jpg"
            ),
            2.25F,
            "1",
            true,
            listOf("cenicero"),
            Categorias.ACCESORIOS.ordinal
        ),
        Producto(
            "7",
            "Botella de vodka a medias",
            "Esta nuevo señores lo digo enserio...",
            "data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAkGBxMTEhUSEhIVFRUVFxUYFxcYGBcVGBYXFxUWFhcXFxcYHSggGBolHRcXITEhJSkrLi4uFx8zODMtNygtLisBCgoKDg0OGhAQFy0lHSUtLS0tLS0vLS0tLS0tLS0tLS0tLS0uLSstLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLf/AABEIALgBEQMBIgACEQEDEQH/xAAcAAACAgMBAQAAAAAAAAAAAAAAAQIDBAUGBwj/xAA+EAABAwIDBQUGBAUDBQEAAAABAAIRAyEEEjEFQVFhgQYicZHwBxMyobHBQtHh8RQjYnKCJDNSQ5KistIW/8QAGQEBAQEBAQEAAAAAAAAAAAAAAAECAwQF/8QAKREBAQACAQMDAwMFAAAAAAAAAAECEQMSITEEQVEiMnEFE2EUgaGx0f/aAAwDAQACEQMRAD8A8WTQhRkk0IQCcpIVAhNJAIQhQJCaSgSE0IpIRKUIArc7MZ/pq5iTlZ0/mtJjy+i08Le7Jqt9w9p369HSPsrEy8NAhTc25UYUaZGz3RUC7Sg2QPXr9lw+HMOb4hdzgLtCNYoV2gA2XKYl2asBzXWbQMNJXG4V01QeLkK6ShQA0WTlt69fvzU6VO3RFcQPXr1yRY0O16kStIFmbUrZnQsRVi0IQhRAhNCBITSQCEIQSQhC0gTSTQCEIRAhCEAkmhFJCZShQIpQpwkopIThOECAUwz+oxF4jjzI+6QRKCRHP1yUCEJSqhLu9lXYPBcIV3PZo5qbTyUdMaq7QPy0yuS2d/uM4yt/2vrWy81odnD+azxRL5dxRZb164LXbZxIa039evV1tgIauQ7RYiXZR1Rq9o1BdJJ4poRCOYQlKkgSE0kQJJoQJCaEUwhCFpAmkmgEITRCTQlPBAIlACaikAhMohRSCE04QKEBShEIIwgJpqoilCmQkghC3mwdvCi3K9riJsRFvMhaYhRyqLKz9ubTFd4LWloHHU+So2bVa2qwv+EG54c1ihCK77HYhvu8zXAiNQQQuEr1Mzi7jp4KvkFIBC0kJwkiBKE0IolEpIQNCjKcomjSQhBNCbmkEg6hJaQIQglAIlJMKbNERxUkIhRQE49eX6+tVCcIGEQgKQCAawkwASTuFyeiym7NrHSlU/7Hfkt12LtUfb8I6X9eS9Nw+Kd/CVGls95sO7vdF5Gk3toudzu7Ph9f036bjy8OPJcvN1r++niVWk5phzS08CCD5FVwuz7ZSaTf7x9DouPyq8efVNvJ670s9Ny9Eu+21cKRCkAoldXiRIQVIqFTcoElU4KQco63QAEeJ+SE2oJHHd+wQQYLpwpU2/NMhBGEiFKNwWw2hgi2nTMN0gw0A66uIu4+KaTbWIKEQo0SSkUkUklJJAkIQg2+2sN/1B1WrldS9gcC06FcxiaJY4tK6ZzVcuO7mkJTCSa5uoTAQmAiBMJgJgKhAJgIhSARAmEw1MNRXRdih36n9rfqV6rs7DTgKx3hzToZXl3YavlqVAHOaSwWBgEAknNxj817RsHA1jg6mSq/M6MpHujF57pO46GVy1rK19z0/qrj6bDHt2vz38/GuzyLtmIo/wCTVwxceK9H9obK1Oi5lV9Qe8fTlpc0tdkzHRo1uD0XnBCnFNS/l5f1PlvJzS2Sdp4u/lZSM6qblGgFaaf3/Zd3zKqUwwHXzQRwRm5+vX0REHUhxlKqLaKx5lDTH5orGClk4q9zpUYU0bRCUKSaqCiO8OS6XF5KmHotDHBwDsz3Wa6+471zVM3WywuLkBj3ZWiYmXCd4gFaxrOc2wa9GLG/CPFYjgthXrAuEC033E+EqjEUYEfeb/bf5KWNSsVIptNkLDRJJlJFJCcoQdW0rA2zhszc41H0WY0qwXsV6LNx5ZdXblGqQC2lHZwNQt6jwWVW2OIOWxC5dFd7nGkATUsqcLKogJwmAnuRSAUgF0ewuxOOxQzUsO4MP46n8tviJufEBdnsr2M1XEGviabBwptLz0c6APIqdUbmFeWNaptpr3R3sw2bh2Zqja1Z1g0GoQXuOgAZlCqwvYbCmo73dNrSDpd4bHDNPmp+5N6064+nyym5Y4Tsr2eczC4nHVe43J7qhJg1HOcM5aNSIAAO+TwXR9k/dDA46nUcGOqNzUxmIcHAEtAI3ggLqdp9jatQtmu6aYhoLWljfAD6rWO9n9cDMA0kcHa9DC3jnL2rjnw5Y3cscliNkHGbLrPpAvrYaqyoWSXPLMmSpE6iDmj+lebQvb//AMti8IDiKOdhcIdBDiQdQWGZ0XB7Z2eXVf59PJm1IaKZ8SBrqtdN8uXVMezkaAUn/T168FmYvBGk4CZafhPyII3EFY77zxRd7UJNCsATLfWo6KCghSZ8vXropFkIHMeO5QQSKkQib/qgSRTSQKCsug6bGLS6TOgnugjjppqdyxbJtMR9EFlVp1A3XOYDpH2UqrJYD3bWAJAcOJ3AyTFyTbwUa9ySQLyYEAAncA2wjgpYQ/hANxGVoLpP4Zadb7gqjBOvighTc3ed0xu6QoTKy0SSZSKikhNCK6QFTa5Ugqxq7vPoVmxDxq2/iOC2eMxDWU80gki0X1FtN6wWrUYx4zQDIGnBS3RMeqqCkiVbhcO+o5tOmxz3us1rQXOPgAuW3okVL0f2c9g3VXsxWJblpDvU2ES6odzi3/iNeduu87C+yrLlr42C4QW0hdreBefxHloOa9Yw2Faz4R1Wbd+HXHGTvWPh8K6xktA8z03LNZRAEQrAVCtVDRJ3KdMi3K5UnYdpiQLacvBVjBMGjQPAQuc2h2meHhtKnmaSAXOMAXiY8VuNlVn1Q4lwEGLAggwDfNuM/JSWXxG7hlhN2sprXNkhx+vyK5rbXbWrhq76TsNna0AhwqBsggGYLTzGu5dKKdX+nqVW/ZTHnNVawkf0iR/kbrWPb2Yy1Z3rXPx76tBldocGubmLHhuYTbLaIi61uJxNPFN/hn4PNnhvvIZ3C7u5heQQSCt5jsE94LGMDWafFqPDcFh7M2C9r3VXwSJFJv4c0Wc48B+fJdZrp7uGX3fS8I2hg2mlXYQWvpNzaz3muGbdab2XJtrkLvDhRnxQzZwKWIGYaOyj4hyMSvPnBMvNYx+2MlplIBV4bWONlkFt7dIEqKgItaTwKg4q0ggTu48b7lB7Y6+HX5Qggbpg31+sDegnW3luP3Rlt6+SgiAkVMhKOHP5ckEAgiyYUHXtp60QW4SDmEaNcRBAuATqbRA032AuoueWuLXtmNQCAR1IIPkoMkGRZTLrRJiek8Y4oaPElrvhBHET8wDJjqeixaeisLiNCol15iAbHxUWEUipFRUUoQmhB0DUqlcN1PRYdbG7m357uixCSbm5XS5fDnMPlk18a51tB61KoWw2JsWtin5KTZj4nH4WjmfsvY+xXYHDYYtq1R76qNCRZp4tbu8dVyufd6+P0+WU6vZwXZX2Z4vFQ+oP4ekfxPHfcP6aeo8XR4Fe1dlex2GwLMtFnePxVHXe/wAXbhyFluBiWDf8lCpjjFmx4rNs925x32jNDYTJWtpYguuSliMUBvTqi/s3embVrgb1y+0touxNT3FAmPxuG6/FSq1HV3miw5Wj4nfbxW6pbPZSp5aQAIvPE8zvC523P8O+OOHD3vfL/X81y+JwraeIax7y4B7YLyJJEm+gXa7OcC3MIMk6ePHf+q0dTZ4dVAqta4kudpYWaBE9fNbV1BtKkadMZb90Di43gbgbrfGxz2ZTHGNgUnDTkVhYSmWsALDIcOGkgk+CmaZ71psAf6oa4dbkLq8twkt7slwk62jTj4pkAiN3DlwWK2j3mkiwZljmcgnykdFosBSNGtnqZmMDahF4YS0fji0QTEhNumPDMpb1eI847Sv9zVxJsczcc2QzIJcGwMm4XgcrryRy9B7VValV1SoWkNJqv+Jx+K5+K53eS89cVuzTydUt7LMMYKyy7gdTETfisOhqswgcLaageOvgpEqB0uTvtu8fonEGZO/lHrxSzn6JVBfS3zjnzQRa3gB+SUQfApvN/D9vXRAI5gb49fJFQPrwTeRytpH7XP5ILToUiOKCJ3j1z0UQpmP0UCoIOUgEnJhRYTlQ/SOcrIcqaoRUlAptMhBRCjkhEIUVc0LrOzPYiticrnAsYbgkXI430C7/ALEezGnSAq4uKlWLM/Az/wCjzXoDcIGgNDO6NzYAXPK32e3g4sJd59/4aHs7sGnhaYpNNidQB8h+a6qkWRGWPqq6bRuClknRZk078mfV5VlsKL2mIWQ4QIN+BWDVxcWaNNT9hxKWJjbl4WtcBaeiqdQzuzO0bu5rDwtYuqEATAvyneVbtfa9DCUXVarw0DUm5J4AbzyUdMpcL28lSikajjYfK8D6haravtNwFB2Q1HVHACRSbnggaF1mz1XmXbX2ivxTTSw7XUqR+JxPffyt8IXCAreGN93j5uaW9u76QZ2zwlYMe5jwIa4EiHNzAb2m3Qre4jFtp1KYit/NcwNJeXMOY/3Eb9F4Zsau91MtIs0Uo816B2V2nVdUw9BziWNqAtB1bYiJ4a2Xpx4fd5MvU+Jp6WGAaACdbIAHH6KRChDROnPy39FzVIDquS7Z7SZTpmm0w45hla7LwkuDdxm0811lMDSNPQXlnbV/+rrAA6j/ANGrrxYy5d3LmzuOHZxu1quajVc3SHgzxDbrzty7La9ZwpuaPhipPULjXJyeWOLwnhx4rKe76T5b+J/VYuHKvkg+tOHjqsR1qYH3+UofPlYEJMfv+Xy3IfroLHmeqqIkaTv3pQLD9JmBqdBv0UmXm156eEKJ9euCgg4WGqAJ19dNYTnh8/pKjm3IEDaI68Eo6pu3qL7KCIElTTpiFY210bVdVVUCvdEKlwQU0zeFIqD9ZVsqCEppyhEfXgAAUH1huTrU26u+qwqsR3SuNtj6HHjMln8QYkiOqYxG8dVryDoVSM4MNaXTuF+pO5Y6nqnFK2NHFEzmM3MLX498ElsF2jR46+SwKO0w2rlcOPRecduu2xObD4Z97ipVb82MP1KkvV4XOTh+qtn2k9oP8I59DBltSpP82sRLQ4fgYBrHGYHNeb7W2xXxL89eq6od06N/taLBYEoldscZHzOXmyzttSlMIpsJWbQpAX9BdZHC11WwKwhw3wzyEL0/2e7J96BiTUj3VUtDQ2c0Ma65m3x8Ny8V2Ri8rzwtI6r2f2Z4uscO5tL3TRnc8h7XGScrQAWkR8PA6Ltcr0s4cUyz1Pjb0dRLB68ly2F7YTUcyo1jA3MM+ZxBIdFgGk316Ld0Nph4BaaZBEg5yLXuQWjeIXDb18npeXj+7FngQuP7T9k6b/f4l1V4Ia+plAbHdZMf+K2bO0bHv93TqU570yHwA0G8kNBvA13rR9tNqV2YXEEYigW+7c3KxvfOYFrhBc6IE3SZa7w/o88u2U1+d/8AHi+2qvcd/l9FyjlssbXLhcnUrWldM7uvHjjJOy3DD181kuiZ3fpr1WLhRKysusEXkCeW+NyzFKCJkRfX8jzugO3c91/QUzSA+JwbyOvkNEV6QEZajajbcQdNC08NFdVncQNgOvjH16/koO1t1G7ot9sfZwdDnObpYGHRwMLo8NgqBHuXMbnJEECGuzEAESZBvMfMrNrtjxWvPJHEzI6dZUXD1oux7T9m6dEF7SSIidcp1B/qG4z9lxZeAcpsQm2csLjdVKVBylP7KJMozE2K6kDPjMb5VQHmsxjmxY3G/Qo0qqUoMkAW3/uVivHrcs6o+bZhbp9Fhv3wlGLVCGGyb1CmVBOEJyhEfWT8GwnM+XeJsPAaK1rG6ACFUaqkHrlqPfblfNWGmFRXxAYOAVeIxYaDJXkHtI7dF2bDYd3J7xuH/Ec1L8Q8TeXhge0XtWKldzMO6Nz3Ded4B+64GVCU5WscZjOzz8nLlyXdSUmNVbVksstyOVXsZoP1jmQFKm5UlykHcNPJbjLYYfBPaPeOhrXfC4uF/CDK3WAxTGZW++qPg3aC4N5wRxK5cP3AnzUxinNFnFal0z9XmXVe04fbeHIYK2GyDuyGPhwGs98knzHiuvw2PwYpF1N9ENiwf7pzm20PfknkV8009rEWc0u/yj7FdTsz2nVaOGfhv4Wi8OtmcSCAQBAygTopl03w1OTl33tr0vH7RpEf6cUqlQ5oAoMa6d2WC4SvLdtYxtapUdVdiGVHuJOrmNkmZEzlFgL7lpn7dL/wBkzmLSe8LWjxnzHC+Niq+X4ajyP7ipdTxDG8mX3Zf5W4/CEUs4cHNbEm4MuNpB3rTOKnVxLiIJMTMSYniqC5ZtJGVhT0WdTfAc78UgDgC6b/ACPmsLBugK/OMpHGPlv+ZC1Es2qpkzNj43W2wGBLnNflABf4C+kDgqNn4YG7pjdYlddhsO73ZdlDAzK7vakNIJn/AIiJ3yVHWTdb7ZtEsH+2TzaWmOGsFZtV7S0yx97TlMjgRzkzO6FGhiso/mNLf6viadb5hp1AWS+u0iQQQZMyIIjWfuub17mvLjNsbcZWwz8zg2s2Wlp7pLgYJaNCNDA0lef4+iQb7l2GNFN2dh7zXV6pEG3wkTY8QuYxd5nUGCumnjzy3dsGi/cpyqNCr2qMLGuTdVKiJNgFL3R32+6KbK0JueqXhRaUEaqpGquqqkoRbCFGUKD6rbUWPicaGi5QhcrX0ZHlntA7bkTQou7xsSNw/NeXEoQt4TUeTlztyCYQhacl1MK1CFYyEwdAmhUAcieKEKiBoqPueaEJoWDDxN9PRSqUeaEJpEBQCPchCFNKsEDT0FlYKk0mXbtxSQqOhwbXfHTbLQAXWtAsMo3nfbcOOvShtR9NzMrA17SA8OLhcRIECdZ3aIQsWvRjPZsMDj+6wO7pIEf8XSJEO+xutR2gLZbSY1rX1CczgBIYB/MdPE2H+Xk0KT5ayvs5TaGCZSquDO7YFpGkEQQRN7g+a0tWpJJI1/JCF0eesGqL9VNqELKMylMWEIqM0zE7+Yn1KEKN6UuMW1HFUPHBCEZQKrKEIGhCFR//2Q==",
            listOf(
                "https://www.worten.es/i/f27fa9006a15d9bd4cc281f97681b14585bc4216.jpg",
                "https://www.worten.es/i/f27fa9006a15d9bd4cc281f97681b14585bc4216.jpg",
                "https://www.worten.es/i/f27fa9006a15d9bd4cc281f97681b14585bc4216.jpg",
                "https://www.worten.es/i/f27fa9006a15d9bd4cc281f97681b14585bc4216.jpg",
                "https://www.worten.es/i/f27fa9006a15d9bd4cc281f97681b14585bc4216.jpg",
                "https://www.worten.es/i/f27fa9006a15d9bd4cc281f97681b14585bc4216.jpg"
            ),
            3.5F,
            "1",
            true,
            listOf("vodka", "alcohol"),
            Categorias.ESPECIAL.ordinal
        )
    )

    override fun getProductsOf(
        idProfile: String
    ): Maybe<List<Producto>> {
        val productsOfProfile = products.filter { producto -> producto.vendedorId == idProfile }
        return Maybe.just(productsOfProfile)
    }

    override fun getAllProducts(): Maybe<List<Producto>> = Maybe.just(products)

    override fun getAllProductsExcept(idProfile: String): Maybe<List<Producto>> = Maybe.just(products.filter { it.vendedorId != idProfile })

    override fun getProductInfo(
        idProducto: String
    ): Maybe<Producto> = Maybe.create { emitter ->
        val producto: Producto? = products.find { product -> product.id == idProducto }

        if (producto != null) {
            emitter.onSuccess(producto)
        } else {
            emitter.onError(Error("There is no product with id $idProducto"))
        }
    }

    override fun getProductsInfo(idProductos: List<String>): Maybe<List<Producto>> = Maybe.create { emitter ->
        val productos = arrayListOf<Producto>()

        idProductos.forEach { id ->
            productos.add(this.products.filter { it.id == id }[0])
        }

        if (productos.isNotEmpty()) {
            emitter.onSuccess(productos)
        } else {
            emitter.onError(Error("There is no product with id $idProductos"))
        }
    }
}
