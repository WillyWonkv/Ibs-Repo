import React, { useState } from "react";
import "./Body.css"
import TopBar from "./top-bar/macro/Top-bar";
import BoxVideoMain from "./video-body/macro/box-video-main/Box-video-main";
import BoxVideoSide from "./video-body/macro/box-video-side/Box-video-side";
import { AutoriList, VideoList } from "./Data";
export interface InfoVideo{

    id : number;
    srcVideo : string;
    srcFotoProfiloVideo : string;
    titoloVideo : string;
    autoreVideo : string;
    numIscrittiVideo : number;
    numLikeVideo : number;
    testoDesciVideo : string
}

const Body = () => {

    const [selectedVideo, setSelectedVideo] = useState<InfoVideo>({
        id : 0,
        srcVideo : "https://www.youtube.com/embed/sAWF5u3T3G8?si=59l-nPSaG1MpS79S",
        srcFotoProfiloVideo : "resources/channels4_profile.jpg",
        titoloVideo : "Unstoppable Beauty of 4K Videos | 4K HDR 120fps | Dolby Vision",
        autoreVideo : "4K Video ULTRA HD",
        numIscrittiVideo : 100000,
        numLikeVideo : 9873,
        testoDesciVideo : ""
    })

    return(

        <div>

            <TopBar></TopBar>

            <div className="body flex">

                <BoxVideoMain 
                    srcPlayer={selectedVideo.srcVideo} 
                    titoloVideo={selectedVideo.titoloVideo} 
                    srcFotoProfilo={selectedVideo.srcFotoProfiloVideo} 
                    nomeProfilo={selectedVideo.autoreVideo} 
                    numIscritti={selectedVideo.numIscrittiVideo}
                    numLike={selectedVideo.numLikeVideo}
                    testoDescri={selectedVideo.testoDesciVideo}
                ></BoxVideoMain>

                <div className="sidebar flex">

                    {VideoList.map((video) =>{
                    
                            const autoreObj = AutoriList.find(a => a.nome === video.autoreVideo);

                            return(<BoxVideoSide 
                                key={video.id}
                                src={video.srcFotoVideo}
                                titolo={video.titoloVideo}
                                autore={video.autoreVideo}
                                numVisual={video.numVisualVideo}
                                idVideo={video.id}
                                idAutore={autoreObj ? autoreObj.id : 0}
                                clickHandler={(InfoVideoSide: InfoVideo) => {
                                    setSelectedVideo(InfoVideoSide);
                                }}
                                
                            />
                            )
                        })
                    }

                </div>

            </div>

        </div>

    );

}

export default Body;