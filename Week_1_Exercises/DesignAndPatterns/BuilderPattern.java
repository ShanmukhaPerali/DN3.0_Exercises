public class BuilderPattern {
    static class Computer {
        private String processor;
        private String memory;
        private String disk;

        private Computer(Builder builder) {
            this.processor = builder.processor;
            this.memory = builder.memory;
            this.disk = builder.disk;
        }

        // Getters
        public String getProcessor() {
            return processor;
        }

        public String getMemory() {
            return memory;
        }

        public String getDisk() {
            return disk;
        }

        public static class Builder {
            private String processor;
            private String memory;
            private String disk;

            public Builder setProcessor(String processor) {
                this.processor = processor;
                return this;
            }

            public Builder setMemory(String memory) {
                this.memory = memory;
                return this;
            }

            public Builder setDisk(String disk) {
                this.disk = disk;
                return this;
            }

            public Computer build() {
                return new Computer(this);
            }
        }
    }

    public static void main(String[] args) {
        Computer gamingComputer = new Computer.Builder()
                .setProcessor("Intel Core i9")
                .setMemory("32GB")
                .setDisk("1TB SSD")
                .build();
        System.out.println("Processor: " + gamingComputer.getProcessor());
        System.out.println("Memory: " + gamingComputer.getMemory());
        System.out.println("Disk: " + gamingComputer.getDisk());
    }
}
