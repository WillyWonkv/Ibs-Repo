import React, { useState } from "react";
import "./Body.css"
import TopBar from "./top-bar/macro/Top-bar";
import BoxVideoMain from "./video-body/macro/box-video-main/Box-video-main";
import BoxVideoSide from "./video-body/macro/box-video-side/Box-video-side";


const Body = () => {

    const [selectedTitle, setSelectedTitle] = useState("Unstoppable Beauty of 4K Videos | 4K HDR 120fps | Dolby Vision");

    return(

        <div>

            <TopBar></TopBar>

            <div className="body flex">

                <BoxVideoMain 
                    srcPlayer={"https://www.youtube.com/embed/sAWF5u3T3G8?si=59l-nPSaG1MpS79S"} 
                    titoloVideo={selectedTitle} 
                    srcFotoProfilo={"resources/channels4_profile.jpg"} 
                    nomeProfilo={"4K Video ULTRA HD"} 
                    numIscritti={100000}
                    numLike={9874}
                    testoDescri={""}
                ></BoxVideoMain>

                <div className="sidebar flex">

                    <BoxVideoSide 
                        src={"resources/hq720.webp"}
                        titolo={"DtMF"}
                        autore={"Bad Bunny"}
                        numVisual={"56 mln"} 
                        clickHandler={(titolo:string) => {
                            setSelectedTitle(titolo)
                        } }                                            
                    ></BoxVideoSide>
{/* 
                    <BoxVideoSide
                        src={"resources/hqdefault.webp"}
                        titolo={"MOJABI GHOST"}
                        autore={"Tainy"} 
                        numVisual={"51 mln"}                    
                    ></BoxVideoSide>

                    <BoxVideoSide
                        src={"resources/hq720.webp"}
                        titolo={"KLOuFRENS"}
                        autore={"Bad Bunny"} 
                        numVisual={"36 mln"}                    
                    ></BoxVideoSide>

                    <BoxVideoSide
                        src={"resources/hq720.webp"}
                        titolo={"VeLDÁ"}
                        autore={"Bad Bunny"} 
                        numVisual={"32 mln"}              
                    ></BoxVideoSide>

                    <BoxVideoSide
                        src={"resources/titi.webp"}
                        titolo={"Tití Me Preguntó"}
                        autore={"Bad Bunny"} 
                        numVisual={"45 mln"}              
                    ></BoxVideoSide>

                    <BoxVideoSide
                        src={"resources/despa.jpg"}
                        titolo={"Despacito"}
                        autore={"Luis Fonsi"} 
                        numVisual={"7.8 Mld"}              
                    ></BoxVideoSide>

                    <BoxVideoSide
                        src={"resources/hqdefault.webp"}
                        titolo={"Hawái"}
                        autore={"Maluma"} 
                        numVisual={"1.2 Mld"}              
                    ></BoxVideoSide>

                    <BoxVideoSide
                        src={"resources/hq720.webp"}
                        titolo={"Blinding Lights"}
                        autore={"The Weeknd"} 
                        numVisual={"2.3 Mld"}              
                    ></BoxVideoSide>

                    <BoxVideoSide
                        src={"resources/hqdefault.webp"}
                        titolo={"Peaches"}
                        autore={"Justin Bieber"} 
                        numVisual={"340 Mln"}              
                    ></BoxVideoSide>

                    <BoxVideoSide
                        src={"resources/hq720.webp"}
                        titolo={"Levitating"}
                        autore={"Dua Lipa"} 
                        numVisual={"1.1 Mld"}              
                    ></BoxVideoSide>

                    <BoxVideoSide
                        src={"resources/hqdefault.webp"}
                        titolo={"Montero (Call Me By Your Name)"}
                        autore={"Lil Nas X"} 
                        numVisual={"850 Mln"}              
                    ></BoxVideoSide>

                    <BoxVideoSide
                        src={"resources/hq720.webp"}
                        titolo={"Stay"}
                        autore={"The Kid LAROI"} 
                        numVisual={"500 Mln"}              
                    ></BoxVideoSide>

                    <BoxVideoSide
                        src={"resources/hqdefault.webp"}
                        titolo={"Industry Baby"}
                        autore={"Lil Nas X"} 
                        numVisual={"600 Mln"}              
                    ></BoxVideoSide> */}


                </div>

            </div>

        </div>

    );

}

export default Body;