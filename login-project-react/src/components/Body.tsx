
import "./Body.css"
import { Button, Card, Flex, Input, InputNumber, Spin } from "antd"
import { useContext, useEffect, useState } from "react"
import { Film, handleDeleteFilm, handleGetAllFilms, handleUpdateFilm } from "../service/FilmsService"
import { DeleteOutlined, EditOutlined, EllipsisOutlined } from "@ant-design/icons"
import { AppContext } from "../App"


export const Body = () =>{

    const [loading, setLoading] = useState(true)   
    const [films, setFilms] = useState<Film[]>([]);
    const [editingFilmId, setEditingFilmId] = useState<number | null>(null);
    const [newFilmData, setNewFilmData] = useState<Partial<Film>>({});
    const [editedFilmData, setEditedFilmData] = useState<Partial<Film>>({});

    const {setStore,store} = useContext(AppContext);

    
    const fetchFilms = ()=>{
        setLoading(true);
        handleGetAllFilms()
        .then(response => {
            setFilms(response);
        })
        .catch(err => {
            console.error("Failed to fetch films", err);
            alert("Failed to fetch films");
        })
        .finally(() => {
            setLoading(false);
        });
    };

    const deleteFilm = async (id:number)=>{
        const ok = await handleDeleteFilm(id);
        if(ok){
            setFilms(prev => prev.filter(f => f.id !== id));
        }else{
            alert("Failed to delete film");
        }
    }

    const handleSaveEdit = async () => {
        await handleUpdateFilm(editedFilmData as Film);
        setFilms(prev => prev.map(f => f.id === editedFilmData.id ? {...f, ...editedFilmData} as Film : f));
        setEditingFilmId(null);
    }

    const handleCancelEdit = () => {
        setEditingFilmId(null);
        setEditedFilmData({});
    }
    
    useEffect(() => {
        fetchFilms();
        console.log(store);
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
                                actions={!store.roles?.includes("ADMIN") ? [
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
                                            <p>{film.duration} min</p>
                                        </>
                                    }
                                />
                                )}
                            </Card>
                        ))}
                        {
                            <Card 
                                key="new-film"
                                hoverable
                                style={{
                                    width: 260,
                                    display: "flex",
                                    flexDirection: "column",
                                    height:"100%"
                                    }}>
                            </Card>
                        }
                    </>
                )
            }
        </div>
    )

}