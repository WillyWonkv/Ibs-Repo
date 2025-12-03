
import "./Body.css"
import { Button, Card, Flex, Input, InputNumber, Select, Spin } from "antd"
import { useContext, useEffect, useState } from "react"
import { Film, Genre, handleCreateFilmService, handleDeleteFilmService, handleGetAllFilmsService, handleGetAllGenresService, handleUpdateFilmService } from "../service/FilmsService"
import { DeleteOutlined, EditOutlined, EllipsisOutlined, PlusCircleOutlined } from "@ant-design/icons"
import { AppContext, openNotification } from "../App"


export const Body = () =>{

    const [loading, setLoading] = useState(true)   
    const [films, setFilms] = useState<Film[]>([]);
    const [genres, setGenres] = useState<Genre[]>([]);

    const [newFilmData, setNewFilmData] = useState<Partial<Film>>({});
    const [isAddingNewFilm, setIsAddingNewFilm] = useState(false);
   
    const [editingFilmId, setEditingFilmId] = useState<number | null>(null);
    const [editedFilmData, setEditedFilmData] = useState<Partial<Film>>({});

    const {setStore,store} = useContext(AppContext);

    const fetchFilms = ()=>{
        setLoading(true);
        handleGetAllFilmsService()
        .then(response => {
            setFilms(response);
        })
        .catch(err => {
            console.error("Failed to fetch films", err);
            openNotification('error', 'Failed to fetch films');
        })
        .finally(() => {
            setLoading(false);
        });
    };

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
            setFilms(prev => prev.filter(f => f.id !== id));
            openNotification('success', 'Film deleted successfully');
        }catch (err) {
            console.error("Failed to delete film", err);
            openNotification('error', 'Failed to delete film');
        }

    }

    const handleSaveEdit = async () => {

        try {            
            await handleUpdateFilmService(editedFilmData as Film);
            setFilms(prev => prev.map(f => f.id === editedFilmData.id ? {...f, ...editedFilmData} as Film : f));
            setEditingFilmId(null);
            setEditedFilmData({});
            openNotification('success', 'Film updated successfully');
        }catch (err) {
            console.error("Failed to update film", err);
            openNotification('error', 'Failed to update film');
        }

    }

    const handleCreateFilm = async () => {

        try {
            await handleCreateFilmService(newFilmData as Film);
            setFilms(prev => [...prev, newFilmData as Film]);
            setNewFilmData({});
            setIsAddingNewFilm(false);
        }catch (err) {
            console.error("Failed to create film", err);
            openNotification('error', 'Failed to create film');
        }
    }

    const handleCancelEdit = () => {
        setEditingFilmId(null);
        setEditedFilmData({});
    }

    
    useEffect(() => {
        fetchFilms();
        fetchGenres();
    }, [])

    return (
        <div
            style={{
                display:"grid",
                gridTemplateColumns: "repeat(auto-fit, 260px)",
                justifyContent: "start",
                gap: "16px",
                padding: "16px",
            }}
            >
            {loading ? (
                <Spin tip="loading" size="large"/>
                ) : (
                    <>
                        {films.map((film) => (

                            <Card 
                                key={film.id}
                                cover={<img draggable={false} alt="Foto" src={film.coverSrc} 
                                        style={{width:"100%",
                                                height: "375px",
                                                objectFit: "cover"
                                        }} />}
                                hoverable
                                variant="borderless"
                                style={{
                                    maxWidth: 260,
                                    display: "flex",
                                    flexDirection: "column",
                                    height: "100%",
                                }}
                                actions={store.roles?.includes("ADMIN") ? [
                                    <EditOutlined
                                    key="edit"
                                    onClick={() => {
                                        setEditingFilmId(film.id);
                                        setEditedFilmData(film);
                                    }}/>,
                                    <DeleteOutlined
                                        key="delete" 
                                        onClick={() => deleteFilm(film.id)}/>,
                                    <EllipsisOutlined key="ellipsis"/>,
                                ] : []}
                            >
                                {editingFilmId === film.id ? (
                                    <>
                                        <span style={{fontSize:"12px"}}>Title:</span>
                                        <Input 
                                            value={editedFilmData.title || ""}
                                            onChange={e => setEditedFilmData(prev => ({
                                                ...prev,
                                                title: e.target.value
                                            }))}
                                            placeholder="Title"
                                            style={{
                                                marginBottom:"8px"
                                            }}
                                        />
                                        <span style={{fontSize:"12px"}}>Description:</span>
                                        <Input.TextArea 
                                            value={editedFilmData.description || ""}
                                            onChange={e => setEditedFilmData(prev => ({
                                                ...prev,
                                                description: e.target.value
                                            }))}
                                            placeholder="Description"
                                            style={{
                                                marginBottom:"8px",
                                                width:"100%",
                                                height:"100px",
                                                resize:"none",
                                                overflowY:"auto"
                                            }}
                                        />
                                        <span style={{fontSize:"12px"}}>Duration (min):</span>
                                        <InputNumber
                                            value={editedFilmData.duration || 0}
                                            onChange={(value) => setEditedFilmData(prev => ({
                                                ...prev,
                                                duration: value || 0
                                            }))}
                                            placeholder={film.duration.toString()}
                                            style={{marginBottom:"8px", width:"100%"}}
                                        />
                                        <Flex gap={8}>
                                            <Button type="primary" onClick={() => handleSaveEdit()}>Save</Button>
                                            <Button onClick={() => handleCancelEdit()}>Cancel</Button>
                                        </Flex>
                                    </>
                                ) : (
                                <Card.Meta 
                                    title= {film.title}
                                    description={
                                        <>
                                            <p
                                                style={{
                                                    display:"-webkit-box",
                                                    WebkitLineClamp: 3,
                                                    overflow: "hidden",
                                                    textOverflow: "ellipsis",
                                                    WebkitBoxOrient: "vertical",
                                                }}
                                            >{film.description}</p>

                                            {film.genres.map(g => (
                                                <span 
                                                    key={g.id} 
                                                    style={{ 
                                                        fontSize: "12px", 
                                                        color: "#888", 
                                                        marginRight: "4px" 
                                                    }}
                                                >
                                                    {g.name}
                                                </span>
                                            ))}
                                            <br/>
                                            <span>{film.duration} min</span>
                                        </>
                                    }
                                />
                                )}
                            </Card>
                        ))}
                        {store.roles?.includes("ADMIN") && (
                            <Card 
                                key="new-film"
                                style={{
                                    position: "relative",
                                    width: 260,
                                    height: "100%",
                                    display: "flex",
                                    flexDirection: "column",
                                    padding: "16px",
                                    }}
                            >
                                {!isAddingNewFilm ? (
                                    <Button 
                                        variant="link" 
                                        color="default" 
                                        onClick={() => setIsAddingNewFilm(true)}
                                        style={{position:"absolute",top:"50%",left:"50%",transform:"translate(-50%,-50%)"}}
                                    >
                                        <PlusCircleOutlined style={{fontSize:"30px"}} />
                                    </Button>
                                ) : (
                                    <div style={{width:"100%"}}>   
                                        
                                        <span style={{fontSize:"16px", fontWeight:"bold"}}>New Film</span>
                                        <br/>
                                        <br/>
                                        
                                        <span style={{fontSize:"12px"}}>Title:</span>
                                        <Input 
                                            value={newFilmData.title || ""}
                                            onChange={e => setNewFilmData(prev => ({...prev, title: e.target.value}))}
                                            placeholder="Title"
                                            style={{
                                                marginBottom:"8px"
                                            }}
                                        />
                                        <span style={{fontSize:"12px"}}>Description:</span>
                                        <Input.TextArea 
                                            value={newFilmData.description || ""}
                                            onChange={e => setNewFilmData(prev => ({...prev, description: e.target.value}))}
                                            placeholder="Description"
                                            style={{
                                                marginBottom:"8px",
                                                width:"100%",
                                                height:"150px",
                                                resize:"none",
                                                overflowY:"auto"
                                            }}
                                        />
                                        <span style={{fontSize:"12px"}}>Duration (min):</span>
                                        <InputNumber
                                            value={newFilmData.duration || 0}
                                            onChange={(value) => setNewFilmData(prev => ({...prev, duration: value || 0}))}
                                            placeholder="Duration"
                                            style={{marginBottom:"8px", width:"100%"}}
                                        />

                                        <span style={{fontSize:"12px"}}>Genres</span>
                                        <Select
                                            mode="multiple"
                                            style={{ width: '100%', marginBottom:"8px" }}
                                            placeholder="Select genres"
                                            value={newFilmData.genres?.map(g => g.id) || []}
                                            onChange={(selectedIds: number[]) =>{
                                                const selectedGenres = genres.filter(g => selectedIds.includes(g.id));
                                                setNewFilmData(prev => ({...prev, genres: selectedGenres}))
                                            }}
                                        >
                                            {genres.map(genre => (
                                                <Select.Option 
                                                    key={genre.id}
                                                    value={genre.id}
                                                >
                                                    {genre.name}
                                                </Select.Option>
                                            ))}
                                        </Select>
                                        <Flex gap={8}>
                                            <Button type="primary" onClick={() => handleCreateFilm()}>Create</Button>
                                            <Button onClick={() => setIsAddingNewFilm(false)}>Cancel</Button>
                                        </Flex>
                                    </div>
                
                                )}
                            </Card>
                            )
                        }
                    </>
                )
            }
        </div>
    )

}