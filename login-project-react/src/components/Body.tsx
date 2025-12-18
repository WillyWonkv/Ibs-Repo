
import "./Body.css"
import { Button, Card, Dropdown, Flex, Input, InputNumber, Pagination, Popconfirm, Select, Spin, } from "antd"
import { useContext, useEffect, useState } from "react"
import { Film, Genre, handleCreateFilmService, handleDeleteFilmService, handleGetAllFilmsPageService, handleGetAllFilmsService, handleGetAllGenresService, handleGetFilmByGenreService, handleGetFilmsByGenrePageService, handleUpdateFilmService } from "../service/FilmsService"
import { DeleteOutlined, DisconnectOutlined, EditOutlined, InboxOutlined, PlusCircleOutlined, } from "@ant-design/icons"
import { AppContext, openNotification } from "../App"
import Dragger from "antd/es/upload/Dragger"
import { FilmCard } from "./FilmCard"
import { GenresDropdown } from "./GenresDropdown"
import { NewFilmCard } from "./NewFilmCard"


export const Body = () =>{

    const [error, setError] = useState(false);
    const [loading, setLoading] = useState(true)   
    const [imgLoading, setImgLoading] = useState(true);

    const [films, setFilms] = useState<Film[]>([]);
    const [genres, setGenres] = useState<Genre[]>([]);

    const [newFilmData, setNewFilmData] = useState<Partial<Film>>({});
    const [isAddingNewFilm, setIsAddingNewFilm] = useState(false);
   
    const [editingFilmId, setEditingFilmId] = useState<number | null>(null);
    const [editedFilmData, setEditedFilmData] = useState<Partial<Film>>({});

    const [page, setPage] = useState(1);
    const [size] = useState(6);
    const [total, setTotal] = useState(0);

    const {store} = useContext(AppContext);

    const fetchFilmsPage = () => {
        setLoading(true);
        setError(false);
    
        const computedSize = store.roles?.includes("ADMIN")
        ? size - 1
        : size;

        handleGetAllFilmsPageService(page, computedSize)
        .then(response => {
            setFilms(response.content);
            setTotal(response.totalElements);
        })
        .catch(err => {
            console.error("Failed to fetch films", err);
            setError(true);
            openNotification('error', 'Failed to fetch films');
        })
        .finally(() => {
            setLoading(false);
        });
    }

    const fetchGenres = ()=>{

        handleGetAllGenresService()
        .then(response => {
            setGenres(response);
        })
        .catch(err => {
            console.error("Failed to fetch genres", err);
            openNotification('error', 'Failed to fetch genres');
        });
    }

    const deleteFilm = async (id:number)=>{

        try {
            await handleDeleteFilmService(id);
            fetchFilmsPage()
            openNotification('success', 'Film deleted successfully');
        }catch (err) {
            console.error("Failed to delete film", err);
            openNotification('error', 'Failed to delete film');
        }

    }

    const handleSaveEdit = async () => {

        try {
            await handleUpdateFilmService(editedFilmData as Film);
            setEditedFilmData({});
            setEditingFilmId(null);
            openNotification('success', 'Film updated successfully');
            fetchFilmsPage();
        }catch (err) {
            console.error("Failed to update film", err);
            openNotification('error', 'Failed to update film');
        }

    }

    const handleCreateFilm = async () => {

        try {
            await handleCreateFilmService(newFilmData as Film);
            setNewFilmData({});
            setIsAddingNewFilm(false);
            fetchFilmsPage();
        }catch (err) {
            console.error("Failed to create film", err);
            openNotification('error', 'Failed to create film');
        }
    }

    const handleFilmsByGenre = async (genreId: number) => {
        setLoading(true);
        setError(false);

        const computedSize = store.roles?.includes("ADMIN")
        ? size - 1
        : size;

        handleGetFilmsByGenrePageService(genreId, page, computedSize)
        .then(response => {
            if(response.content.length === 0){
                openNotification('info', 'No films found for the selected genre');
                setFilms([]);
            }
            setFilms(response.content);
            setTotal(response.totalElements);
        })
        .catch(err => {
            console.error("Failed to fetch films by genre", err);
            setError(true);
            openNotification('error', 'Failed to fetch films by genre');
        })
        .finally(() => {
            setLoading(false);
        });
    }
  
    useEffect(() => {

        fetchFilmsPage();

    }, [store.token, store.roles, page, size])

    useEffect(() => {
        if(store.films && store.films.length > 0){
            setFilms(store.films);
        }
    }, [store.films]);

    return (
            
        <>

            {loading && <Spin tip="loading" size="large" 
                        style={{
                            position: "absolute",
                            top: "50%",
                            left: "50%",
                            transform: "translate(-50%, -50%)"
                        }}/>
            }

            {error && 
                <div 
                    style={{
                            position: "absolute",
                            top: "50%",
                            left: "50%",
                            transform: "translate(-50%, -50%)"
                    }}
                    >
                    <DisconnectOutlined style={{fontSize:70}}/>
                    {error}
                </div>  
            }            

            {!loading && !error && (
                <>
                    <div style={{
                        padding:"16px",
                    }}>
                        <GenresDropdown
                            genres={genres}
                            onClickAll={() => {
                                setPage(1)
                                fetchFilmsPage()
                            }}
                            onClickGenre={(genreId: number) => {
                                setPage(1)
                                handleFilmsByGenre(genreId)
                            }}
                            fetchGenres={fetchGenres}
                        />
                    </div>

                    <div
                        style={{
                            display:"grid",
                            gridTemplateColumns: "repeat(auto-fit, 260px)",
                            justifyContent: "start",
                            gap: "16px",
                            padding: "16px",
                        }}
                        >       
                        {films.map((film) => (
                            <FilmCard
                                key={film.id}
                                film={film}
                                genres={genres}
                                imgLoading={imgLoading}
                                setImgLoading={setImgLoading}
                                isAdmin={store.roles?.includes("ADMIN") === true}
                                isEditing={editingFilmId === film.id}
                                editedFilmData={editedFilmData}
                                setEditedFilmData={setEditedFilmData}
                                onEdit={() => {
                                    setEditingFilmId(film.id);
                                    setEditedFilmData(film);
                                    fetchGenres();
                                }}
                                onDelete={() => deleteFilm(film.id)}
                                onCancelEdit={() => {
                                    setEditingFilmId(null);
                                    setEditedFilmData({});
                                }}
                                onSaveEdit={handleSaveEdit}
                            />
                        ))}
                        {store.roles?.includes("ADMIN") && (
                                <NewFilmCard
                                    isAdding={isAddingNewFilm}
                                    genres={genres}
                                    newFilmData={newFilmData}
                                    setIsAdding={setIsAddingNewFilm}
                                    setNewFilmData={setNewFilmData}
                                    onCreate={handleCreateFilm}
                                    fetchGenres={fetchGenres}
                                />
                            )
                        }    
                    </div>
                    <div
                        style={{
                            margin: "20px 0 50px 0",
                        }}
                    >
                        <br></br>
                        <Pagination
                            simple={{ readOnly: true }}
                            current={page}
                            pageSize={size}
                            total={total}
                            onChange={(newPage) => {
                                setImgLoading(true)
                                setPage(newPage)
                            }}
                            showSizeChanger={false}
                        />
                    </div>
                </>
            )}


        
        </>
    )

}