package me.escoffier.pirate.hibernate;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import io.smallrye.mutiny.Uni;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Random;

@Entity
@Table(name = "pirates")
public class Pirate extends PanacheEntity {

    @Column(length = 60, unique = true)
    public String name;

    @Override
    public String toString() {
        return "Pirate{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public static Uni<Pirate> getRandomPirate() {
        Random random = new Random();
        return Pirate.count()
                .onItem().transform(l -> random.nextInt(l.intValue()))
                .onItem().transformToUni(i -> Pirate.findById((long) i));
    }
}
