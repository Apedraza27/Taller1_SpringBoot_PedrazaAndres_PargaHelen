package Model;

import jakarta.persistence.Entity;

@Entity
public class Productos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_productos;


}
