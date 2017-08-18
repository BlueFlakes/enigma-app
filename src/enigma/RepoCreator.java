package enigma;

public class RepoCreator {

    static ServiceRepository createRepository() {
		ServiceRepository repo = new ServiceRepository();

		repo.register(new Rot13Enigma());
		repo.register(new CaesarEnigma());
		repo.register(new VigenereEnigma());
		repo.register(new AtbashEnigma());
		repo.register(new SquareMatrixEnigma());
		repo.register(new XorEnigma());
		repo.register(new BaconEnigma());
        repo.register(new RailFenceEnigma());
        repo.register(new PolybiusSquareEnigma());
        repo.register(new SubstitutionEnigma());

		return repo;
	}
}
