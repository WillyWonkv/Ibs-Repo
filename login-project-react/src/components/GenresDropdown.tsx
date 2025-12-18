import React from "react";

import { Button, Dropdown } from "antd";
import { Genre } from "../service/FilmsService";

interface GenresDropdownProps {
    genres: Genre[];

    onClickAll: () => void;
    onClickGenre: (genreId: number) => void;
    fetchGenres: () => void;
    
}


export const GenresDropdown = ({
    genres,
    onClickAll,
    onClickGenre,
    fetchGenres
}:GenresDropdownProps) => {

    return (
        <Dropdown 
            trigger={["click"]}
            onOpenChange={(open) => {
                if(open && genres.length === 0) {
                    fetchGenres();
                } 
            }}
            menu={{
                items: [
                    { 
                        key: 'grid',
                        label: (
                        <div
                            style={{
                                display:"grid",
                                gridTemplateColumns: "repeat(3, 1fr)",
                                gap: "8px",
                                maxHeight: "300px",
                                overflowY: "auto",
                                padding:"8px",
                            }}
                            >
                            {<Button
                                type="text"
                                size="small"
                                onClick={onClickAll}
                            >
                                All Genres
                            </Button>}
                            {genres.map((genre) => (
                                <Button 
                                    key={genre.id}  
                                    type="text"
                                    size="small"
                                    onClick={() => onClickGenre(genre.id)}
                                >
                                    {genre.name}
                                </Button>
                            ))}
                        </div>)
                    }          
                ]
            }}
        >
            <Button 
                type="default"
                onClick={(e) => {e.preventDefault();}}
                >
                Genres
            </Button>    
        </Dropdown>

    )

}